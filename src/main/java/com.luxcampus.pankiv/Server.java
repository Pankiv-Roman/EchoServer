package com.luxcampus.pankiv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        OutputStream outputStream = null;
        InputStream inputStream;
        while (true) {

            inputStream = socket.getInputStream();

            byte[] buffer = new byte[100];

            int count = inputStream.read(buffer);
            String sentence = new String(buffer, 0, count);

            if (sentence.equals("exit")) {
                break;
            }
            sentence = new String("Echo: " + sentence);
            outputStream = socket.getOutputStream();
            outputStream.write(sentence.getBytes());
        }
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
