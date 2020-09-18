package com.krab.rest.consumer;

import com.krab.rest.dto.AuthorDto;
import com.krab.rest.services.AuthorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private final AuthorsService authorsService;

    @Autowired
    public KafkaConsumer(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @KafkaListener(topics = "messages", groupId = "group_id")
    public void consumeMessages(String message) {
        System.out.println("message = " + message);
    }

    @KafkaListener(topics = "addAuthor", groupId = "group_author_id", containerFactory = "concurrentKafkaListenerContainerFactoryAuthor")
    public void consumeAuthors(AuthorDto author) {
        log.info("Add author from MQ Value = " + author);
        authorsService.addAuthor(author.getFirstName(), author.getLastName());
    }
}
