package com.newqur.testnetty.class03_tcpnodelay;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

/**
 *
 * https://blog.csdn.net/huang_xw/article/details/7340241
 *
 * @author charles-desktop
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setTcpNoDelay(true);
        socket.connect(new InetSocketAddress("localhost", 10000));
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String head = "hello";
        String body = "world\r\n";
        for (int i = 0; i < 10; i++) {
            long label = System.currentTimeMillis();
            out.write(head.getBytes());
            out.write(body.getBytes());
            String line = reader.readLine();
            System.out.println("rtt: " + (System.currentTimeMillis() - label) + ", received: " + line);
        }
        in.close();
        out.close();
        socket.close();
    }
}
