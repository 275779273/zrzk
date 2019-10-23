package com.zrzk.baidumap.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//客户端到服务端
public class CoverService {
    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //创建服务器端ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(8090);

        //一直接收客户端发过来的连接
        while (true) {
            //接收客户端的连接
            Socket socket = serverSocket.accept();
            //使用线程池执行任务
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        //ObjectInputStream objIS = new ObjectInputStream(socket.getInputStream());
                        //Cover cover = (Cover) objIS.readObject();
                        //System.out.println(cover.getGenreName());
                        is = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len = is.read(bytes);
                        System.out.println(new String(bytes, 0, len));

                        //向客户端回一条消息
                        os = socket.getOutputStream();
                        os.write("接收到消息".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (socket != null) {
                                socket.close();
                            }
                            if (is != null) {
                                is.close();
                            }
                            if (os != null) {
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
