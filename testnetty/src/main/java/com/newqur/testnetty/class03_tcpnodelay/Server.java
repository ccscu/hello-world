package com.newqur.testnetty.class03_tcpnodelay;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author charles-desktop
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(10000));
        System.out.println("Server start at 10000");
        while (true) {
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            while (true) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = br.readLine();
                    System.out.println(line);
                    os.write((line + "\r\n").getBytes());
                } catch (Exception ex) {
                    break;
                }

            }
        }
    }
}
