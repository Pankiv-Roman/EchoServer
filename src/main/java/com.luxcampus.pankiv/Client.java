package com.luxcampus.pankiv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Enter your massage!");
        OutputStream outputStream;
        InputStream inputStream = null;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String massage = scanner.nextLine();

            outputStream = socket.getOutputStream();
            outputStream.write(massage.getBytes());
            if (massage.equals("exit")) {
                break;
            }
            inputStream = socket.getInputStream();

            byte[] buffer = new byte[100];

            int count = inputStream.read(buffer);
            String sentence = new String(buffer, 0, count);
            System.out.println(sentence);
        }
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
