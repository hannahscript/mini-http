package com.github.hannahscript.minihttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientReader;
    private PrintWriter clientWriter;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(this.port);

        this.clientSocket = this.serverSocket.accept();
        this.clientReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);


        this.clientWriter.println("Connected to mini-http");
        String clientMessage;
        do {
            clientMessage = this.clientReader.readLine();
            clientWriter.println("You sent '" + clientMessage + "'");
        } while(!clientMessage.equals(""));
    }

    public void stop() throws IOException {
        this.clientReader.close();
        this.clientWriter.close();
        this.clientSocket.close();
        this.serverSocket.close();
    }
}
