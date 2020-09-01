package com.github.hannahscript.minihttp.protocols.dict.commands;

import com.github.hannahscript.minihttp.protocols.dict.CommandVisitor;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

public class SetCommand implements Command {
    private final String word;
    private final String definition;

    public SetCommand(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    @Override
    public Response accept(CommandVisitor visitor) {
        return visitor.visitSet(this);
    }
}
