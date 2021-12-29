package com.example.tgbot.enums;

public enum Commands {
    PROFILE("/profile"),
    CARDS("/cards"),
    BACK("/back"),
    START("/start");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
