package com.example.tgbot.service;

import com.example.tgbot.enums.Commands;
import com.example.tgbot.enums.Labels;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CommandsHandler {

    public SendMessage getCommandResponse(String text, User user, Update update){
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

        if(text.equals(Labels.LINK.getText())) {
            return handleLinkCommand();
        }

        return handleNotFoundCommand();
    }

    public SendMessage handleNotFoundCommand() {
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

    public SendPhoto handleImageCommand() throws IOException {
        SendPhoto message = new SendPhoto();
        InputFile file = new InputFile();
        InputStream in = new URL("https://picsum.photos/200").openStream();
        file.setMedia(in, "Image");
        message.setPhoto(file);
        message.setCaption("Рандомная картинка");
        message.setReplyMarkup(new Keyboard().getMainKeyboard());
        return message;
    }

    private SendMessage handleLinkCommand(){
        SendMessage message = new SendMessage();
        message.setText("http://google.com");
        //message.setReplyMarkup(new Keyboard().getMainKeyboard());
        return message;
    }

    public EditMessageText handleEditCommand(String chatId, Integer messageId){
        return new EditMessageText(chatId, messageId, null, "new test",
                ParseMode.HTML, true, null , null);
    }

    public DeleteMessage handleDeleteCommand(Update update){
        DeleteMessage delete = new DeleteMessage();
        delete.setChatId(String.valueOf(update.getMessage().getChatId()));
        delete.setMessageId(update.getMessage().getReplyToMessage().getMessageId());
        return delete;
    }
}
