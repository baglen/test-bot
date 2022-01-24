package com.example.tgbot.data.repository;

import com.example.tgbot.data.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findById(Long id);

    Optional<Operator> findByLogin(String login);
}
