package com.shen.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/17 4:14 下午
 * @desc:
 */
//1.添加注解
@ServerEndpoint("/websocket/{examNumber}")
public class WebSocket {
    private static volatile int onlineCount = 0;
    //用来存放每个客户端对应的WebSocket对象，适用于同时与多个客户端通信（线程安全的无序的集合）
    public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    //若要实现服务端与指定客户端通信的话，可以使用Map来存放，其中Key可以为用户标识（适用于并发）
    public static ConcurrentHashMap<Session, Object> webSocketMap = new ConcurrentHashMap<Session, Object>();

    private static ConcurrentHashMap<String, WebSocket> webSocket = new ConcurrentHashMap<String, WebSocket>();
    //与某个客户端的连接会话，通过它实现定向推送(只推送给某个用户)
    private Session session;
    private String examNumber="";
    /**
     * 建立连接成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "examNumber") String param,Session session) {
        System.out.println("=="+param);
        this.session = session;
        examNumber = param;
        webSocketSet.add(this);  // 添加到set中
        webSocketMap.put(session,this);

        webSocket.put(param,this);    // 添加到map中

        addOnlineCount();    // 添加在线人数
        System.out.println("新人加入，当前在线人数为：" + getOnlineCount());
    }

    /**
     * 收到客户端调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        //业务逻辑处理  教师端发送到学生端
            System.out.println("客户端发来的消息"+message);
            String[] msg=message.split("&&");
            if(msg[0].equals("beginExam")){//开始考试
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
           }else if(msg[0].equals("breach")){//违纪
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
        }else if(msg[0].equals("pauseExam")){//暂停考试
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
            }else if(msg[0].equals("resumeExam")){//恢复考试
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
            }else if(msg[0].equals("ForcedExam")){//强制交卷考试
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
            }else if(msg[0].equals("cheat")){//作弊
                if(webSocket.get(msg[1])!=null){
                    webSocket.get(msg[1]).sendMessage(msg[0]);
                }else{
                    System.out.println("当前用户不在线");
                }
            }else if(msg[0].equals("admin")){//学生端发送成绩到教师端
                if(webSocket.get(msg[0])!=null){
                    webSocket.get(msg[0]).sendMessage(msg[1]);
                }else{
                    System.out.println("教师端不在线");
                }
            }

//        for (WebSocket item :
//                webSocketSet) {
//            item.sendAllMessage(message);
//        }
    }
      public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
     }
    /**
     * 关闭连接调用的方法
     */
      @OnClose
    public void onClose(Session closeSession) {
        webSocketMap.remove(session);
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有人离开，当前在线人数为：" + getOnlineCount());
    }



    // 获取在线人数
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    // 添加在线人+1
    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    // 减少在线人-1
    public static synchronized void subOnlineCount() {
        onlineCount--;
    }
}
