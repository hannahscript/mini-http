package com.github.hannahscript.minihttp.protocols.dict;

import com.github.hannahscript.minihttp.protocols.dict.commands.Command;
import com.github.hannahscript.minihttp.protocols.dict.commands.GetCommand;
import com.github.hannahscript.minihttp.protocols.dict.commands.OkResponse;
import com.github.hannahscript.minihttp.protocols.dict.commands.SetCommand;
import com.github.hannahscript.minihttp.protocols.dict.responses.AnswerResponse;
import com.github.hannahscript.minihttp.protocols.dict.responses.ErrorResponse;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

import java.util.HashMap;
import java.util.Map;

public class CommandVisitor {
    private Map<String, String> definitions = new HashMap<>();

    public Response visit(Command host) {
        return host.accept(this);
    }

    public Response visitGet(GetCommand command) {
        String definition = this.definitions.get(command.getWord());
        return definition == null ? new ErrorResponse() : new AnswerResponse(definition);
    }

    public Response visitSet(SetCommand command) {
        this.definitions.put(command.getWord(), command.getDefinition());
        return new OkResponse();
    }
}