package br.com.diogenes.maxclubcard.dataprovider.card;

import br.com.diogenes.maxclubcard.core.domain.card.Card;
import br.com.diogenes.maxclubcard.core.domain.card.CardOut;
import br.com.diogenes.maxclubcard.core.gateway.ClientGateway;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.mapper.CardMapper;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardGatewayImplTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ClientGateway clientGateway;

    @InjectMocks
    private CardGatewayImpl cardGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterCardInRepositoryWhenRegisterCardIsCalled() {
        ClientEntity clientEntity = new ClientEntity(1L, "Diogenes", "12312312312", LocalDate.now(), "Masculino", "mail@mail.com", "77112234455");
        Card card = new Card("1234", "12/23", "Credit","MAXCLUBCARD", "1234");
        CardEntity cardEntity = new CardEntity(1l, "1234","12/23", "Credit","MAXCLUBCARD",
                new ClientEntity(1l, "Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455"));

        when(clientGateway.getClientEntity(anyString())).thenReturn(clientEntity);
        when(cardRepository.save(any())).thenReturn(cardEntity);

        cardGateway.registerCard(card);

        verify(cardRepository).save(any());
    }

    @Test
    void shouldReturnCardEntityWhenGetCardEntityIsCalled() {
        CardEntity cardEntity = new CardEntity(1L, "1234", "12/23", "Credit", "MAXCLUBCARD", new ClientEntity());

        when(cardRepository.findByCardNumber(any())).thenReturn(cardEntity);

        CardEntity result = cardGateway.getCardEntity("1234");

        assertEquals(cardEntity, result);
    }

    @Test
    void shouldReturnCardOutWhenGetCardIsCalled() {
        CardEntity cardEntity = new CardEntity(1l, "1234","12/23", "Credit","MAXCLUBCARD",
                new ClientEntity(1l, "Joe", "1234", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455"));

        CardOut cardOut = new CardOut(1l, "1234","12/23", "Credit","MAXCLUBCARD", "1234");

        when(cardRepository.findByCardNumber(anyString())).thenReturn(cardEntity);

        CardOut result = cardGateway.getCard("1234");

        assertEquals(cardOut, result);
    }
}