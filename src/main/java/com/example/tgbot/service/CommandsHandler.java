package com.example.tgbot.service;

import com.example.tgbot.enums.Commands;
import com.example.tgbot.enums.Labels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

public class CommandsHandler {

    public SendMessage getCommandResponse(String text, User user){
        if (text.equals(Labels.PROFILE_LABEL.getText())) {
            return handleProfileCommand(user.getUserName());
        }

        if (text.equals(Labels.CARDS_LABEL.getText())) {
            return handleCardsCommand();
        }

        if (text.equals(Commands.START.getCommand())) {
            return handleStartCommand();
        }

        if(text.equals(Labels.BACK_LABEL.getText())) {
            return handleBackCommand();
        }

        return handleNotFoundCommand();
    }

    private SendMessage handleNotFoundCommand() {
        SendMessage message = new SendMessage();
        message.setText("Команда в разработке");
        message.setReplyMarkup(new Keyboard().getMainKeyboard());
        return message;
    }

    private SendMessage handleStartCommand() {
        SendMessage message = new SendMessage();
        message.setText("Выберите нужную команду");
        message.setReplyMarkup(new Keyboard().getMainKeyboard());
        return message;
    }

    private SendMessage handleBackCommand() {
        SendMessage message = new SendMessage();
        message.setText("Вы вернулись в гланый раздел, выберите нужную команду");
        message.setReplyMarkup(new Keyboard().getMainKeyboard());
        return message;
    }

    private SendMessage handleProfileCommand(String profileName){
        SendMessage message = new SendMessage();
        message.setText("Ваш профиль: " + profileName);
        message.setReplyMarkup(new Keyboard().getProfileKeyboard());
        return message;
    }

    private SendMessage handleCardsCommand(){
        SendMessage message = new SendMessage();
        message.setText("Раздел 'Карты', выберите нужную команду");
        message.setReplyMarkup(new Keyboard().getCardsKeyboard());
        return message;
    }
}
