package com.github.hannahscript.minihttp.protocols.dict.commands;

import com.github.hannahscript.minihttp.protocols.dict.CommandVisitor;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

/**
 * Defines a client command
 */
public interface Command {
    Response accept(CommandVisitor visitor);
}
