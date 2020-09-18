package com.krab.rest.consumer;

import com.krab.rest.configs.KafkaConfig;
import com.krab.rest.dto.AuthorDto;
import com.krab.rest.services.AuthorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private final AuthorsService authorsService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaConsumer(AuthorsService authorsService, KafkaTemplate<String, String> kafkaTemplate) {
        this.authorsService = authorsService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaConfig.MESSAGES_TOPIC, groupId = "group_id")
    public void consumeMessages(String message) {
        System.out.println("message = " + message);
    }

    @KafkaListener(topics = KafkaConfig.ADD_AUTHOR_TOPIC, groupId = "group_author_id", containerFactory = "concurrentKafkaListenerContainerFactoryAuthor")
    public void consumeAuthors(AuthorDto author) {
        log.info("Add author from MQ. Value = " + author);
        AuthorDto addedAuthor = authorsService.addAuthor(author.getFirstName(), author.getLastName());
        kafkaTemplate.send(KafkaConfig.MESSAGES_TOPIC, "Author added from MQ. Value = " + addedAuthor);
    }
}
