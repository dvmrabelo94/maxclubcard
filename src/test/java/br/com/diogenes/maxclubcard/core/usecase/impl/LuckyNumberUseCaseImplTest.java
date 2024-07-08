package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LuckyNumberUseCaseImplTest {

    @Mock
    private LuckyNumberGateway luckyNumberGateway;

    @InjectMocks
    private LuckyNumberUseCaseImpl luckyNumberUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnLuckyNumberWhenDrawLuckyNumberIsCalled() {
        LuckyNumber luckyNumber1 = new LuckyNumber("1234", true);
        List<LuckyNumber> luckyNumberList = Arrays.asList(luckyNumber1);

        when(luckyNumberGateway.findByIsValid()).thenReturn(luckyNumberList);

        LuckyNumber result = luckyNumberUseCase.drawLuckyNumber();

        assertEquals(luckyNumber1, result);
    }
}