package com.example.tgbot.service;

import com.example.tgbot.enums.Commands;
import com.example.tgbot.enums.Labels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@Component
public class TgBotHandler extends TelegramLongPollingBot {

    @Value("${telegram.name}")
    private String name;

    @Value("${telegram.token}")
    private String token;
    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if(text.equals(Labels.IMAGE.getText())) {
                try {
                    SendPhoto message = new CommandsHandler().handleImageCommand();
                    message.setParseMode(ParseMode.HTML);
                    message.setChatId(String.valueOf(chatId));
                    execute(message);
                } catch (IOException | TelegramApiException e) {
                    SendMessage messageEx = new SendMessage();
                    messageEx.setText("Ошибка загрузки картинки");
                    messageEx.enableHtml(true);
                    messageEx.setParseMode(ParseMode.HTML);
                    messageEx.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(messageEx);
                    } catch (TelegramApiException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if(text.contains((Commands.EDIT.getCommand()))){
                EditMessageText editMessage = new CommandsHandler()
                        .handleEditCommand(chatId.toString(), update.getMessage().getReplyToMessage().getMessageId());
                editMessage.setText(text.replace(Commands.EDIT.getCommand()+" ", ""));
                try {
                    execute(editMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    SendMessage messageEx = new SendMessage();
                    messageEx.setText("Ошибка изменения сообщения");
                    messageEx.enableHtml(true);
                    messageEx.setParseMode(ParseMode.HTML);
                    messageEx.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        execute(messageEx);
                    } catch (TelegramApiException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if(text.equals(Labels.DELETE.getText())){
                DeleteMessage deleteMessage = new CommandsHandler().handleDeleteCommand(update);
                try {
                    execute(deleteMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    SendMessage message = new CommandsHandler()
                            .getCommandResponse(text, update.getMessage().getFrom(), update);
                    message.enableHtml(true);
                    message.setParseMode(ParseMode.HTML);
                    message.setChatId(String.valueOf(chatId));
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
