package com.example.tgbot.service;

import com.example.tgbot.enums.Commands;
import com.example.tgbot.enums.Labels;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        keyboard.setKeyboard(rows);
        return keyboard;
        /*InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonProfile = new InlineKeyboardButton();
        buttonProfile.setText(Labels.PROFILE_LABEL.getText());
        buttonProfile.setCallbackData(Commands.PROFILE.getCommand());

        InlineKeyboardButton buttonCard = new InlineKeyboardButton();
        buttonCard.setText(Labels.CARDS_LABEL.getText());
        buttonCard.setCallbackData(Commands.CARDS.getCommand());

        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonProfile);
        keyboardButtonsRow1.add(buttonCard);

        keyboardButtons.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);

        return inlineKeyboardMarkup;*/
    }

    public ReplyKeyboardMarkup getProfileKeyboard(){
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(Labels.BACK_LABEL.getText());
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        keyboard.setKeyboard(rows);
        return keyboard;
        /*InlineKeyboardMarkup profileKeyboard = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonBack = new InlineKeyboardButton();
        buttonBack.setText(Labels.BACK_LABEL.getText());
        buttonBack.setCallbackData(Commands.BACK.getCommand());

        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(buttonBack);

        profileKeyboard.setKeyboard(keyboardButtons);
        return profileKeyboard;*/
    }

    public ReplyKeyboardMarkup getCardsKeyboard(){
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
        /*InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonBack = new InlineKeyboardButton();
        buttonBack.setText(Labels.BACK_LABEL.getText());
        buttonBack.setCallbackData(Commands.BACK.getCommand());

        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(buttonBack);

        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;*/
    }
}
