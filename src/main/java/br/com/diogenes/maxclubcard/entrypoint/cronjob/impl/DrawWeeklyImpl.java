package br.com.diogenes.maxclubcard.entrypoint.cronjob.impl;

import br.com.diogenes.maxclubcard.core.usecase.LuckyNumberUseCase;
import br.com.diogenes.maxclubcard.entrypoint.cronjob.DrawWeekly;
import br.com.diogenes.maxclubcard.entrypoint.procuder.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DrawWeeklyImpl implements DrawWeekly {

    private final ProducerService producerService;
    private final LuckyNumberUseCase luckyNumberUseCase;
    public DrawWeeklyImpl(ProducerService producerService, LuckyNumberUseCase luckyNumberUseCase) {
        this.producerService = producerService;
        this.luckyNumberUseCase = luckyNumberUseCase;
    }

    @Value("${maxclub.topic}")
    private String topic;

    @Scheduled(cron = "${schedule.weekly.cron}")
    public void executeTask() {
        var luckyNumber = luckyNumberUseCase.drawLuckyNumber();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var luckyNumberJson = objectMapper.writeValueAsString(luckyNumber);
            producerService.send(topic, luckyNumberJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
