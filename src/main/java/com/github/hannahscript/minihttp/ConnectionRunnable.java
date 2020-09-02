package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.ResponseProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Runs for each client connected to the server
 */
public class ConnectionRunnable implements Runnable {
    private final Socket clientSocket;
    private final BufferedReader clientReader;
    private final PrintWriter clientWriter;
    private final ResponseProtocol protocol;

    private boolean running = false;

    /**
     * Creates a new runnable, accepting the client socket and a protocol to run for the client
     * @param clientSocket
     * @param protocol This protocol will be run for the client
     * @throws IOException
     */
    public ConnectionRunnable(Socket clientSocket, ResponseProtocol protocol) throws IOException {
        this.clientSocket = clientSocket;
        this.protocol = protocol;
        this.clientReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        this.running = true;

        this.clientWriter.println("Connected to mini-http");
        String clientMessage;
        do {
            try {
                clientMessage = this.clientReader.readLine();
            } catch (IOException e) {
                break;
            }

            // End of stream, i.e. socket closed
            if (clientMessage == null) {
                break;
            }

            String response = this.protocol.respond(clientMessage);
            this.clientWriter.println(response);
        } while(this.running && !clientMessage.equals(""));
    }

    public void stop() throws IOException {
        this.running = false;
        this.clientSocket.close();
        this.clientReader.close();
        this.clientWriter.close();
    }
}
