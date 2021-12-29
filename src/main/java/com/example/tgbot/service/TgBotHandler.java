package com.example.tgbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
                try {
                    SendMessage message = new CommandsHandler().getCommandResponse(text, update.getMessage().getFrom());
                    message.enableHtml(true);
                    message.setParseMode(ParseMode.HTML);
                    message.setChatId(String.valueOf(update.getMessage().getChatId()));
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }
    }
}
