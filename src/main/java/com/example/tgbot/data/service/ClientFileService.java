package com.example.tgbot.data.service;

import com.example.tgbot.data.model.ClientFile;
import com.example.tgbot.data.repository.ClientFileRepository;
import com.example.tgbot.data.repository.OperatorRepository;
import com.example.tgbot.exception.SaveClientFileException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.OffsetDateTime;

@Service
public class ClientFileService {

    private final ClientFileRepository clientFileRepository;
    private final OperatorRepository operatorRepository;

    public ClientFileService(ClientFileRepository clientFileRepository, OperatorRepository operatorRepository) {
        this.clientFileRepository = clientFileRepository;
        this.operatorRepository = operatorRepository;
    }

    public void saveClientFile(File file) {
        ClientFile clientFile = null;
        try {
            clientFile = new ClientFile(
                    FileUtils.readFileToByteArray(file),
                    file.getName(),
                    OffsetDateTime.now(),
                    1L
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveClientFileException("Failed to read photo");
        }
        clientFileRepository.save(clientFile);
    }

}
