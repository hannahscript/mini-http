package com.github.hannahscript.minihttp.protocols.dict.responses;

public class AnswerResponse implements Response {
    private final String definition;

    public AnswerResponse(String definition) {
        this.definition = definition;
    }

    @Override
    public String serialize() {
        return "ANSWER " + this.definition;
    }
}
