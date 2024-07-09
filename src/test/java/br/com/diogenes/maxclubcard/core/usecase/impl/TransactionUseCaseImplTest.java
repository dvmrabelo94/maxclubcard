package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.TransactionGateway;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionUseCaseImplTest {

    @Mock
    private TransactionGateway transactionGateway;

    @Mock
    private CardGateway cardGateway;

    @Mock
    private BrandCardGateway brandCardGateway;

    @InjectMocks
    private TransactionUseCaseImpl transactionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterTransactionInGatewayWhenRegisterTransactionIsCalled() {
        Transaction transaction = new Transaction("1234", "12/23", BigDecimal.valueOf(300.00), LocalDateTime.now());
        TransactionOut transactionOut = new TransactionOut(List.of(new LuckyNumber("1234", true), new LuckyNumber("1235", true)));
        CardOut cardOut = new CardOut(1L,"1234", "12/23", "Credit", "MAXCLUBCARD", "1234");

        when(cardGateway.getCard(any())).thenReturn(cardOut);
        when(transactionGateway.registerTransaction(any())).thenReturn(transactionOut);
        when(brandCardGateway.findAllByIsActiveTrue()).thenReturn(List.of(new BrandEntity(1L, "MAXCLUBCARD", true)));
        transactionUseCase.registerTransaction(transaction);

        verify(transactionGateway).registerTransaction(any());
    }

    @Test
    void shouldThrowBusinessExceptionWhenCardIsInvalid() {
        Transaction transaction = new Transaction("1234", "12/23", BigDecimal.valueOf(100.00), LocalDateTime.now());
        CardOut cardOut = new CardOut(1l, "1234",  "12/23", "INVALID", "Elo", "1234");

        when(cardGateway.getCard(any())).thenReturn(cardOut);
        when(brandCardGateway.findAllByIsActiveTrue()).thenReturn(List.of());

        assertThrows(BusinessException.class, () -> transactionUseCase.registerTransaction(transaction));
    }
}