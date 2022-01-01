package com.example.tgbot.enums;

public enum Commands {
    EDIT("/edit"),
    START("/start");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
