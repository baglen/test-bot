package com.example.tgbot.enums;

public enum Labels {
    PROFILE_LABEL("Профиль"),
    CARDS_LABEL("Карты"),
    CARDS_INFO_LABEL("Мои карты"),
    ADD_CARD_LABEL("Добавить карту"),
    IMAGE("Картинка"),
    LINK("Ссылка"),
    DELETE("Удалить"),
    BACK_LABEL("Назад");

    private final String text;

    Labels(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
