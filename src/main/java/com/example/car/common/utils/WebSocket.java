package com.example.car.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息推送
 *
 * @author chenkang
 */

@ServerEndpoint("/websocket/{userid}")
@Service
@Slf4j
public class WebSocket {

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static volatile int onlineCount = 0;

    // 用来存放每个客户端对应的MyWebSocket对象。可以使用Map来存放(遍历的用set)，其中Key可以为用户标识
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    // private List<WebSocket> webSocketSet = new ArrayList<WebSocket>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String Key;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("userid") String adminId, Session session) {
        this.session = session;
        clients.put("admin" + adminId, this);
        Key = "admin" + adminId;
        System.out.println(Key);
        addOnlineCount(); // 在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount() + "加入的管理员ID为:" + adminId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        clients.remove(Key); // 从set中删除
        subOnlineCount(); // 在线数减1
        //log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("控制器收到消息：{}", message);
        JSONObject jsonObject = JSONObject.fromObject(message);
        System.out.println("admin" + jsonObject.get("touser").toString());
        send2User(message, "admin" + jsonObject.get("touser").toString());
    }

    /**
     * 给指定的人发送消息
     *
     * @param message
     */
    private void send2User(String message, String key) {
        try {
            if (clients.get(key) != null) {
                clients.get(key).sendMessage(message);
            } else {
                log.info("当前用户不在线");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error(error.toString());
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送给所有
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}
