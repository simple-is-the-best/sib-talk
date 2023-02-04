package com.sib.chat.domain.command;

import lombok.Value;

@Value(staticConstructor = "of")
public class CreateCommand implements Command{
    String test;
}