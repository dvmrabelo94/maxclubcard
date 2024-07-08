package br.com.diogenes.maxclubcard.entrypoint.procuder.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ProducerServiceImplTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private ProducerServiceImpl producerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSendInKafkaTemplateWhenSendIsCalled() {
        String topic = "testTopic";
        Object message = new Object();

        producerService.send(topic, message);

        verify(kafkaTemplate).send(any(), any());
    }
}