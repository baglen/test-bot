package com.example.tgbot.enums;

public enum Commands {
    EDIT("/изменить"),
    START("/start");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
