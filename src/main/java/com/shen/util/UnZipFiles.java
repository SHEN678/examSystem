package com.shen.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class UnZipFiles {

    /**
     * 解压
     * @param zipfile 压缩包文件
     * @param dest 目标文件
     * @param pwd 密码
     * @throws ZipException 抛出异常
     */
    public static boolean unZip(String zipfile, String dest, String pwd) throws ZipException {
         ZipFile zipFile2 = new ZipFile(zipfile);
        try {
            long startTime = System.currentTimeMillis();
            //设置编码格式
            zipFile2.setFileNameCharset("GBK");
            if (!zipFile2.isValidZipFile()) {
                throw new ZipException("文件不合法或不存在");
            }
            //检查是否需要密码
            checkEncrypted(zipFile2,pwd);
            //进行解压
            List<FileHeader> fileHeaderList = zipFile2.getFileHeaders();
            for (int i = 0; i < fileHeaderList.size(); i++) {
                FileHeader fileHeader = fileHeaderList.get(i);
                zipFile2.extractFile(fileHeader, dest);
            }
            System.out.println("解压成功！");
            long endTime = System.currentTimeMillis();
            System.out.println("耗时：" + (endTime - startTime) + "ms");
            return  true;
        }catch (Exception e){
            //删除解压失败出来的文件
            String fileName = zipFile2.getFile().getName().substring(0, zipFile2.getFile().getName().lastIndexOf("."));
            System.out.println("解压出来的文件名字"+fileName);
            String path=dest+File.separator+fileName;
            System.out.println("解压出来文件的路径"+path);
            boolean fileCheck=deleteFile(new File(path));
              if(fileCheck){
                  System.out.println("删除解压失败的文件成功");
              }else{
                  System.out.println("删除解压失败的文件失败");
              }
            System.out.println("解压失败");
            return false;

        }
    }
    //检测密码
    private static void checkEncrypted(ZipFile zip,String pwd) throws ZipException {
        if (zip.isEncrypted()) {
            System.out.println("文件"+zip.getFile().getName()+"有密码！");
            zip.setPassword(pwd);
        }
    }
    //删除解压失败出来的文件夹
    public static boolean deleteFile(File file) {
        if (file.exists()) {//判断文件是否存在
            if (file.isFile()) {//判断是否是文件
                file.delete();//删除文件
                return true;
            } else if (file.isDirectory()) {//否则如果它是一个目录
                File[] files = file.listFiles();//声明目录下所有的文件 files[];
                for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
                    deleteFile(files[i]);//把每个文件用这个方法进行迭代
                }
                file.delete();//删除文件夹
                return true;
            }
        } else {
            System.out.println("所删除的文件不存在");
        }
        return false;
    }
}
