package com.github.hannahscript.minihttp.protocols;

/**
 * Defines a simple protocol in which the client makes a request and the server sends a response
 */
public interface ResponseProtocol {
    String respond(String text);
}
