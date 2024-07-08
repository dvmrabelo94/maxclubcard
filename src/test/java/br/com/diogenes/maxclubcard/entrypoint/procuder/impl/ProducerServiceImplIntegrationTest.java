package br.com.diogenes.maxclubcard.entrypoint.procuder.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.config.name=application-test")
@EmbeddedKafka(partitions = 1, topics = { "testTopic" })
public class ProducerServiceImplIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ProducerServiceImpl producerService;

    private CountDownLatch latch;
    private String receivedMessage;

    @BeforeEach
    void setUp() {
        latch = new CountDownLatch(1);
    }

    @Test
    void shouldSendMessageToKafka() throws InterruptedException {
        String topic = "testTopic";
        String message = "testMessage";

        producerService.send(topic, message);

        boolean await = latch.await(10, TimeUnit.SECONDS);
        assertThat(await).isTrue();
        assertThat(receivedMessage).isEqualTo(message);
    }

    @KafkaListener(topics = "testTopic", groupId = "testGroup")
    public void listen(ConsumerRecord<String, String> record) {
        receivedMessage = record.value();
        latch.countDown();
    }
}