package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.facade.CardFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class CardControllerImplTest {

    @Mock
    private CardFacade cardService;

    @InjectMocks
    private CardControllerImpl cardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallCreateCardInServiceWhenCreateCardIsCalled() {
        CardRequest cardRequest = new CardRequest("1234567890123456", "12/99", "Credit", "Visa", "12345678900");
        cardController.registerCard(cardRequest);
        verify(cardService).registerCard(any(CardRequest.class));
    }
}