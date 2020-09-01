package com.github.hannahscript.minihttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientReader;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(this.port);

        this.clientSocket = this.serverSocket.accept();
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        clientReader.readLine();
    }

    public void stop() throws IOException {
        this.clientReader.close();
        this.clientSocket.close();
        this.serverSocket.close();
    }
}
