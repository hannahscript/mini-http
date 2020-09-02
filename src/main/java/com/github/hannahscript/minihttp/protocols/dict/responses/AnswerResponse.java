package com.github.hannahscript.minihttp.protocols.dict.responses;

/**
 * The answer response replies to the get command with a word definition
 */
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
