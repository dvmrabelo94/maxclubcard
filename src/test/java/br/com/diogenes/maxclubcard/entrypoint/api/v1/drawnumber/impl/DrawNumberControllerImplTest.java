package br.com.diogenes.maxclubcard.entrypoint.api.v1.drawnumber.impl;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.usecase.LuckyNumberUseCase;
import br.com.diogenes.maxclubcard.entrypoint.procuder.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DrawNumberControllerImplTest {

    @Mock
    private ProducerService producerService;

    @Mock
    private LuckyNumberUseCase luckyNumberUseCase;

    @InjectMocks
    private DrawNumberControllerImpl drawNumberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallExecuteTaskInUseCaseWhenExecuteTaskIsCalled() throws Exception {
        LuckyNumber luckyNumber = new LuckyNumber("1234", true);
        ObjectMapper objectMapper = new ObjectMapper();
        String luckyNumberJson = objectMapper.writeValueAsString(luckyNumber);

        when(luckyNumberUseCase.drawLuckyNumber()).thenReturn(luckyNumber);

        LuckyNumber result = drawNumberController.executeTask().getBody();

        verify(luckyNumberUseCase).drawLuckyNumber();
        verify(producerService).send(any(), anyString());
        assertEquals(luckyNumber, result);
    }
}