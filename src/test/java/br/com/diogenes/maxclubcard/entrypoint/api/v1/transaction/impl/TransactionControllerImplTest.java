package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.facade.TransactionFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class TransactionControllerImplTest {

    @Mock
    private TransactionFacade transactionFacade;

    @InjectMocks
    private TransactionControllerImpl transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterTransactionInFacadeWhenGenerateLuckyNumberIsCalled() {
        TransactionRequest transactionRequest = new TransactionRequest("1234567890123456", "12/23", BigDecimal.valueOf(100.0), LocalDateTime.now());
        transactionController.generateLuckyNumber(transactionRequest);
        verify(transactionFacade).registerTransaction(any(TransactionRequest.class));
    }
}