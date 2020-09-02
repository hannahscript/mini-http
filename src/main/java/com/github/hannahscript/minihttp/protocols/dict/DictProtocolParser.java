package com.github.hannahscript.minihttp.protocols.dict;

import com.github.hannahscript.minihttp.protocols.dict.commands.Command;
import com.github.hannahscript.minihttp.protocols.dict.commands.GetCommand;
import com.github.hannahscript.minihttp.protocols.dict.commands.SetCommand;

/**
 * Parses incoming commands for the dictionary protocol
 */
public class DictProtocolParser {
    public Command parse(String text) {
        String[] parts = text.split(" ", 2);

        Verb verb = Verb.fromString(parts[0]);

        if (verb == null) {
            return null;
        }

        switch (verb) {
            case GET:
                return parseGet(parts[1]);
            case SET:
                return parseSet(parts[1]);
            default:
                return null;
        }
    }

    private Command parseGet(String rest) {
        if (rest.length() <= 0) {
            return null;
        }

        return new GetCommand(rest);
    }

    private Command parseSet(String rest) {
        String[] args = rest.split(" ", 2);

        if (args.length < 2 || args[0].length() <= 0 || args[1].length() <= 0) {
            return null;
        }

        return new SetCommand(args[0], args[1]);
    }
}
