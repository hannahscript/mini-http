package com.github.hannahscript.minihttp.protocols.dict.responses;

/**
 * The error response is sent for syntax errors or when a word definition is not known
 */
public class ErrorResponse implements Response {
    @Override
    public String serialize() {
        return "ERROR";
    }
}
