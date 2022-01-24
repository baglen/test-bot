package com.example.tgbot.data.service;

import com.example.tgbot.data.model.ClientFile;
import com.example.tgbot.data.repository.ClientFileRepository;
import com.example.tgbot.data.repository.OperatorRepository;
import com.example.tgbot.exception.ObjectNotFoundException;
import com.example.tgbot.exception.SaveClientFileException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ClientFileServiceImpl implements ClientFileService{

    private final ClientFileRepository clientFileRepository;
    private final OperatorRepository operatorRepository;

    public ClientFileServiceImpl(ClientFileRepository clientFileRepository, OperatorRepository operatorRepository) {
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

    public ClientFile getFileByName(String fileName){
        Optional<ClientFile> file = clientFileRepository.findByFileName(fileName);
        if(file.isPresent()){
            return file.get();
        } else {
            throw new ObjectNotFoundException("File with name: " + fileName + " not found");
        }
    }

}
