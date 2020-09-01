package com.github.hannahscript.minihttp.protocols.dict.responses;

public class ErrorResponse implements Response {
    @Override
    public String serialize() {
        return "ERROR";
    }
}
