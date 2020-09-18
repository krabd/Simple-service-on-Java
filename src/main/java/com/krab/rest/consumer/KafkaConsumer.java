package com.krab.rest.consumer;

import com.krab.rest.dto.AuthorDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "messages", groupId = "group_id")
    public void consumeMessages(String message) {
        System.out.println("message = " + message);
    }

    @KafkaListener(topics = "addAuthor", groupId = "group_author_id", containerFactory = "concurrentKafkaListenerContainerFactoryAuthor")
    public void consumeAuthors(AuthorDto author) {
        System.out.println("message = " + author);
    }
}
