package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.core.usecase.LuckyNumberUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class LuckyNumberUseCaseImpl implements LuckyNumberUseCase {

    private final LuckyNumberGateway luckyNumberGateway;

    public LuckyNumberUseCaseImpl(LuckyNumberGateway luckyNumberGateway) {
        this.luckyNumberGateway = luckyNumberGateway;
    }

    @Override
    public LuckyNumber drawLuckyNumber() {
        var luckyNumberList = luckyNumberGateway.findByIsValid();
        if (luckyNumberList.isEmpty()) {
            throw new IllegalArgumentException("No lucky number available");
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(luckyNumberList.size());
        var drawNumber = luckyNumberList.get(indiceAleatorio);
        luckyNumberGateway.updateAllByLuckyNumbers(false, getIdsLuckyNumber(luckyNumberList));
        return drawNumber;
    }

    private List<String> getIdsLuckyNumber(List<LuckyNumber> luckyNumberList) {
        return luckyNumberList.stream()
                .map(LuckyNumber::luckyNumber)
                .toList();
    }

}
