package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.dict.DictProtocol;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class HttpServerTest {
    private static final int PORT = 80;

    private HttpServer server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private String read() throws IOException {
        return this.reader.readLine();
    }

    private void write(String text) {
        this.writer.println(text);
    }

    @BeforeEach
    void before() throws IOException {
        this.server = new HttpServer(PORT, new DictProtocol());
        new Thread(() -> {
            try {
                this.server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        this.socket = new Socket("127.0.0.1", PORT);
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @AfterEach
    void after() throws IOException {
        this.socket.close();
        this.writer.close();
        this.reader.close();
        this.server.stop();
    }

    @Test
    @DisplayName("When you connect, you see a greeting")
    void test_whenConnect_thenSeeGreeting() throws IOException {
        String response = read();
        assertEquals("Connected to mini-http", response);
    }

    @Test
    @DisplayName("When you try to get an undefined word, it replies with an error")
    void test_whenGetAndNoDefinition_thenError() throws IOException {
        read(); // Greeting
        write("GET word");
        String response = read();
        assertEquals("ERROR", response);
    }

    @Test
    @DisplayName("When you try to get the empty word, it replies with an error")
    void test_whenGetEmptyWord_thenError() throws IOException {
        read(); // Greeting
        write("GET ");
        String response = read();
        assertEquals("ERROR", response);
    }

    @Test
    @DisplayName("When you define a word and then get it, it replies with your definition")
    void test_whenGetAndDefinedPreviously_thenError() throws IOException {
        read(); // Greeting
        write("SET word hello world");
        String response = read();
        assertEquals("OK", response);
        write("GET word");
        response = read();
        assertEquals("ANSWER hello world", response);
    }

    @Test
    @DisplayName("When you send an unknown command, it replies with an error")
    void test_whenUnkownCommand_thenError() throws IOException {
        read(); // Greeting
        write("GIT commit");
        String response = read();
        assertEquals("ERROR", response);
    }
}
