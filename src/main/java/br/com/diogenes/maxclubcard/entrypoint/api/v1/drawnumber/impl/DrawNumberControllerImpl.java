package br.com.diogenes.maxclubcard.entrypoint.api.v1.drawnumber.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.usecase.LuckyNumberUseCase;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.drawnumber.DrawNumberController;
import br.com.diogenes.maxclubcard.entrypoint.procuder.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrawNumberControllerImpl implements DrawNumberController {

    private final ProducerService producerService;
    private final LuckyNumberUseCase luckyNumberUseCase;
    public DrawNumberControllerImpl(ProducerService producerService, LuckyNumberUseCase luckyNumberUseCase) {
        this.producerService = producerService;
        this.luckyNumberUseCase = luckyNumberUseCase;
    }

    @Value("${maxclub.topic}")
    private String topic;

    public ResponseEntity<LuckyNumber> executeTask() {
        var luckyNumber = luckyNumberUseCase.drawLuckyNumber();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var luckyNumberJson = objectMapper.writeValueAsString(luckyNumber);
            producerService.send(topic, luckyNumberJson);
            return ResponseEntity.ok(luckyNumber);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
