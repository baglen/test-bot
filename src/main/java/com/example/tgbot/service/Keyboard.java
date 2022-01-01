package com.example.tgbot.service;

import com.example.tgbot.enums.Labels;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    public ReplyKeyboardMarkup getMainKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Labels.PROFILE_LABEL.getText());
        keyboardRow.add(Labels.CARDS_LABEL.getText());
        keyboardRow.add(Labels.IMAGE.getText());
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(Labels.LINK.getText());
        keyboardRow1.add(Labels.DELETE.getText());
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        rows.add(keyboardRow1);
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    public ReplyKeyboardMarkup getProfileKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Labels.BACK_LABEL.getText());
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        keyboard.setKeyboard(rows);
        return keyboard;
    }

    public ReplyKeyboardMarkup getCardsKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Labels.CARDS_INFO_LABEL.getText());
        keyboardRow.add(Labels.ADD_CARD_LABEL.getText());
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(Labels.BACK_LABEL.getText());
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        rows.add(keyboardRow1);
        keyboard.setKeyboard(rows);
        return keyboard;
    }
}
