package com.example.tgbot.enums;

public enum Labels {
    PROFILE_LABEL("Профиль"),
    CARDS_LABEL("Карты"),
    CARDS_INFO_LABEL("Мои карты"),
    ADD_CARD_LABEL("Добавить карту"),
    BACK_LABEL("Назад");

    private String text;

    Labels(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
