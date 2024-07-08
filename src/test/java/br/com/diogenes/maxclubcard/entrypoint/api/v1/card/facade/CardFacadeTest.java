package br.com.diogenes.maxclubcard.entrypoint.api.v1.card.facade;

import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.usecase.CardUseCase;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.mapper.CardMapperEntryPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardFacadeTest {

    @Mock
    private CardUseCase cardUseCase;

    @InjectMocks
    private CardFacade cardFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterCardInUseCaseWhenRegisterCardIsCalled() {
        CardRequest cardRequest = new CardRequest("1234567890123456", "12/99", "Credit", "Visa", "12345678900");
        CardOut cardResponse = new CardOut(1L, "1234567890123456", "12/99", "Credit", "Visa", "12345678900");

        when(cardUseCase.registerCard(any())).thenReturn(cardResponse);

        cardFacade.registerCard(cardRequest);

        verify(cardUseCase).registerCard(any());
    }
}