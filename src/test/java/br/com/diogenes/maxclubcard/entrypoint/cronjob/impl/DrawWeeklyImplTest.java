package br.com.diogenes.maxclubcard.entrypoint.cronjob.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.usecase.LuckyNumberUseCase;
import br.com.diogenes.maxclubcard.entrypoint.procuder.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DrawWeeklyImplTest {

    @Mock
    private ProducerService producerService;

    @Mock
    private LuckyNumberUseCase luckyNumberUseCase;

    @InjectMocks
    private DrawWeeklyImpl drawWeekly;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSendInProducerServiceWhenExecuteTaskIsCalled() throws Exception {
        String luckyNumberJson = "{\"luckyNumber\":\"1234\",\"isValid\":true}";
        when(luckyNumberUseCase.drawLuckyNumber()).thenReturn(new LuckyNumber("1234", true));
        drawWeekly.executeTask();
        verify(producerService).send(any(), eq(luckyNumberJson));
    }
}