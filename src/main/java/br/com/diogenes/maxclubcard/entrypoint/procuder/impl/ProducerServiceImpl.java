package br.com.diogenes.maxclubcard.entrypoint.procuder.impl;

import br.com.diogenes.maxclubcard.entrypoint.procuder.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, Object message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info(String.format("M=send, message=Mensagem enviada para o topico: '%s'", message));
        } catch (Exception e) {
            log.error(String.format("M=send, message=Não foi possível enviar a mensagem para o topico kafka, exceptionMessage=%s", e.getMessage()));
        }
    }
}
