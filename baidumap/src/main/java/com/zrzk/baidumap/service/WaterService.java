package com.zrzk.baidumap.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//服务端到客服端
public class WaterService {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(9090);
        Scanner sc = new Scanner(System.in);
        while (true) {

            pool.submit(new Runnable() {
                OutputStream os = null;
                Socket accept = null;
                InputStream is = null;
                String next = sc.next();
                @Override
                public void run() {
                    try {

                        accept = serverSocket.accept();
                        //ObjectInputStream objIS = new ObjectInputStream(accept.getInputStream());
                        //Object obj = objIS.readObject();
                        os = accept.getOutputStream();
                        os.write(next.getBytes());

                        is = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len = is.read(bytes);
                        //while (len > 0) {
                            System.out.println(new String(bytes, 0, len));
                        //}

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (os != null) {
                                os.close();
                            }
                            if (is != null) {
                                is.close();
                            }
                            if (accept != null) {
                                accept.close();
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
