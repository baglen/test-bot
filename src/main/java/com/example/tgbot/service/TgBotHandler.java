package com.example.tgbot.service;

import com.example.tgbot.data.service.ClientFileService;
import com.example.tgbot.enums.Commands;
import com.example.tgbot.enums.Labels;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class TgBotHandler extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(TgBotHandler.class);

    private final ClientFileService clientFileService;

    @Value("${telegram.name}")
    private String name;

    @Value("${telegram.token}")
    private String token;

    public TgBotHandler(ClientFileService clientFileService) {
        this.clientFileService = clientFileService;
    }

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
        if(update.hasMessage() && update.getMessage().hasPhoto()){
            List<PhotoSize> photos = update.getMessage().getPhoto();
            PhotoSize photo = photos.get(photos.size() - 1);
            String id = photo.getFileId();
            try {
                GetFile getFile = new GetFile();
                getFile.setFileId(id);
                File filePath = execute(getFile);
                String fileName = "data/userDoc/" + update.getMessage().getFrom().getUserName() + "_"
                        + filePath.getFileId() + ".png";
                java.io.File file = downloadFile(filePath, new java.io.File(fileName));
                clientFileService.saveClientFile(file);
                SendMessage message = new SendMessage();
                message.setText("Ваш файл успешно загружен");
                message.setReplyMarkup(new Keyboard().getMainKeyboard());
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getLocalizedMessage());
            }
        }
        else if(update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (text.equals(Labels.IMAGE_LABEL.getText())) {
                try {
                    SendPhoto message = new CommandsHandler().handleImageCommand();
                    message.setParseMode(ParseMode.HTML);
                    message.setChatId(String.valueOf(chatId));
                    execute(message);
                } catch (IOException | TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                    try {
                        execute(new CommandsHandler()
                                .handleExceptionResponse("Ошибка загрузки картинки", chatId.toString()));
                    } catch (TelegramApiException ex) {
                        log.error(ex.getLocalizedMessage());
                    }
                }
            } else if (text.contains((Commands.EDIT.getCommand()))) {
                EditMessageText editMessage = new CommandsHandler()
                        .handleEditCommand(chatId.toString(), update.getMessage().getReplyToMessage().getMessageId());
                editMessage.setText(text.replace(Commands.EDIT.getCommand() + " ", ""));
                try {
                    execute(editMessage);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                    try {
                        execute(new CommandsHandler()
                                .handleExceptionResponse("Ошибка изменения сообщения", chatId.toString()));
                    } catch (TelegramApiException ex) {
                        log.error(ex.getLocalizedMessage());
                    }
                }
            } else if (text.equals(Labels.DELETE_LABEL.getText())) {
                DeleteMessage deleteMessage = new CommandsHandler().handleDeleteCommand(update);
                try {
                    execute(deleteMessage);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            } else {
                log.info("else");
                try {
                    SendMessage message = new CommandsHandler()
                            .getCommandResponse(text, update.getMessage().getFrom());
                    message.enableHtml(true);
                    message.setParseMode(ParseMode.HTML);
                    message.setChatId(String.valueOf(chatId));
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error(e.getLocalizedMessage());
                }
            }
        }
    }
}
