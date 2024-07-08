package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardUseCaseImplTest {

    @Mock
    private CardGateway cardGateway;

    @InjectMocks
    private CardUseCaseImpl cardUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterCardInGatewayWhenRegisterCardIsCalled() {
        Card card = new Card("1234",  "12/23", "Debit","MAXCLUBCARD", "1234");
        CardOut cardOut = new CardOut(1l, "1234", "12/23", "Debit", "MAXCLUBCARD", "1234");

        when(cardGateway.registerCard(any())).thenReturn(cardOut);

        cardUseCase.registerCard(card);

        verify(cardGateway).registerCard(any());
    }

    @Test
    void shouldThrowBusinessExceptionWhenCardIsInvalid() {
        Card card = new Card("1234",  "12/23", "Credit", "VISA", "1234");

        assertThrows(BusinessException.class, () -> cardUseCase.registerCard(card));
    }
}