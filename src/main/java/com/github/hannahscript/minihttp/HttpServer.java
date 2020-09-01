package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.ResponseProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;
    private final ResponseProtocol protocol;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientReader;
    private PrintWriter clientWriter;

    public HttpServer(int port, ResponseProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
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
            String response = this.protocol.respond(clientMessage);
            this.clientWriter.println(response);
        } while(!clientMessage.equals(""));
    }

    public void stop() throws IOException {
        this.clientReader.close();
        this.clientWriter.close();
        this.clientSocket.close();
        this.serverSocket.close();
    }
}
