package com.example.tgbot.data.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "client_file")
public class ClientFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] fileBytes;
    private String fileName;
    private OffsetDateTime created;
    private Long operatorId;

    public ClientFile() {
    }

    public ClientFile(byte[] fileBytes, String fileName, OffsetDateTime created, Long operatorId) {
        this.fileBytes = fileBytes;
        this.fileName = fileName;
        this.created = created;
        this.operatorId = operatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
