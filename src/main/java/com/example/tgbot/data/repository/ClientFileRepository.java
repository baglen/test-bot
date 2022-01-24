package com.example.tgbot.data.repository;

import com.example.tgbot.data.model.ClientFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientFileRepository extends JpaRepository<ClientFile, Long> {
    List<ClientFile> findByOperatorId(Long id);
    ClientFile save(ClientFile file);
    Optional<ClientFile> findByFileName(String fileName);
}
