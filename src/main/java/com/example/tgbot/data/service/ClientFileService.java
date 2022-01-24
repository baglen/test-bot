package com.example.tgbot.data.service;

import com.example.tgbot.data.model.ClientFile;

import java.io.File;

public interface ClientFileService {
    void saveClientFile(File file);

    ClientFile getFileByName(String name);
}
