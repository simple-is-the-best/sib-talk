package com.sib.chat.domain.command;

import java.util.function.Supplier;

@FunctionalInterface
public interface CommandSupplier extends Supplier<Command> {
}