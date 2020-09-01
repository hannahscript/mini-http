package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.ResponseProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;
    private final ResponseProtocol protocol;

    private ServerSocket serverSocket;
    private boolean running = false;

    public HttpServer(int port, ResponseProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    public void start() throws IOException {
        this.running = true;
        this.serverSocket = new ServerSocket(this.port);

        while (this.running) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException ex) {
                // Rethrow if still running, otherwise assume socket has been closed by stop method and just quit
                if (this.running) {
                    throw ex;
                }
                break;
            }
            // TODO limit thread capacity somehow (threadpool with 0 queue size? + somehow gracefully turn away clients when at max. cap)
            new Thread(new ConnectionRunnable(clientSocket, this.protocol)).start();
        }
    }

    public void stop() throws IOException {
        this.running = false;
        this.serverSocket.close();
    }
}
