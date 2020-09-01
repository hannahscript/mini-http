package com.github.hannahscript.minihttp;

import com.github.hannahscript.minihttp.protocols.dict.DictProtocol;

import java.io.IOException;

public class MiniHttp {
    private static final int PORT = 80;

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(PORT, new DictProtocol());
        server.start();
    }
}
