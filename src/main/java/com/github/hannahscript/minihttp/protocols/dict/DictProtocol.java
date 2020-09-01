package com.github.hannahscript.minihttp.protocols.dict;

import com.github.hannahscript.minihttp.protocols.ResponseProtocol;
import com.github.hannahscript.minihttp.protocols.dict.commands.Command;
import com.github.hannahscript.minihttp.protocols.dict.responses.ErrorResponse;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

public class DictProtocol implements ResponseProtocol {
    private final ProtocolParser protocolParser = new ProtocolParser();
    private final CommandVisitor commandVisitor = new CommandVisitor();

    public String respond(String text) {
        Command command = this.protocolParser.parse(text);

        Response response = command == null ?
                new ErrorResponse() :
                this.commandVisitor.visit(command);

        return response.serialize();
    }
}
