package com.github.hannahscript.minihttp.protocols.dict.commands;

import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

public class OkResponse implements Response {
    @Override
    public String serialize() {
        return "OK";
    }
}
