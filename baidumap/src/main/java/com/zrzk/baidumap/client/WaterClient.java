package com.zrzk.baidumap.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WaterClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        while (true) {
            try {

                socket = new Socket("127.0.0.1", 9090);
                is = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len = is.read(bytes);
                System.out.println(new String(bytes, 0, len));

                //返回数据
                os = socket.getOutputStream();
                os.write("ok".getBytes());
            } catch (IOException e) {
                //返回数据
                os = socket.getOutputStream();
                os.write("no".getBytes());
                e.printStackTrace();
            } finally {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }
}
