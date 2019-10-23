package com.zrzk.baidumap.client;

import com.zrzk.baidumap.pojo.Cover;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoverClient {
    //打印日志
    private final static Logger logger = Logger.getLogger(CoverClient.class.getName());

    public static void main(String[] args) throws IOException {

        Cover cover = new Cover();
        cover.setGenreName("压力传感器");
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                socket = new Socket("127.0.0.1", 8090);
                String next = sc.next();
                //socket = new Socket("127.0.0.1", 8090);
                //获取对象输出流
                //ObjectOutputStream objOS = new ObjectOutputStream(socket.getOutputStream());
                os = socket.getOutputStream();
                //发送到服务器
                os.write(next.getBytes());
                //objOS.writeObject(cover);


                //获取输入流
                is = socket.getInputStream();
                //读取服务器返回的数据
                byte[] bytes = new byte[1024];
                int len = is.read(bytes);
                System.out.println(new String(bytes, 0, len));
            } catch (IOException e) {
                logger.log(Level.SEVERE, null, e);
            } finally {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (socket != null) {
                    socket.close();
                }

            }

        }
    }
}
