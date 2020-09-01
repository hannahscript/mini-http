package com.github.hannahscript.minihttp.protocols.dict.commands;

import com.github.hannahscript.minihttp.protocols.dict.CommandVisitor;
import com.github.hannahscript.minihttp.protocols.dict.responses.Response;

public interface Command {
    Response accept(CommandVisitor visitor);
}
