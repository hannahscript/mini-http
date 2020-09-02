package com.github.hannahscript.minihttp.protocols.dict.responses;

public class OkResponse implements Response {
    @Override
    public String serialize() {
        return "OK";
    }
}
