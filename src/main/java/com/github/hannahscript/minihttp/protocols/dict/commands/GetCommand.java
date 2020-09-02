package com.github.hannahscript.minihttp.protocols.dict.commands;

import com.github.hannahscript.minihttp.protocols.dict.CommandVisitor;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

/**
 * The get command asks for the definition of a word
 */
public class GetCommand implements Command {
    private final String word;

    public GetCommand(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public Response accept(CommandVisitor visitor) {
        return visitor.visitGet(this);
    }
}
