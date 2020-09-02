package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.ResponseProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server that runs a simple response protocol on a port, accepting mulitple connections.
 */
public class HttpServer {
    private final int port;
    private final ResponseProtocol protocol;

    private ServerSocket serverSocket;
    private boolean running = false;

    /**
     * Creates a new server
     * @param port Port that the server will accept connections on
     * @param protocol Protocol that the server will run
     */
    public HttpServer(int port, ResponseProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * Starts the server
     * @throws IOException
     */
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

    /**
     * Stops the server
     * @throws IOException
     */
    public void stop() throws IOException {
        this.running = false;
        this.serverSocket.close();
    }
}
