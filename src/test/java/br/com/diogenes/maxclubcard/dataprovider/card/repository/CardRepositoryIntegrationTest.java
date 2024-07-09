package br.com.diogenes.maxclubcard.dataprovider.card.repository;

import br.com.diogenes.maxclubcard.core.domain.client.Client;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.config.name=application-test")
@ExtendWith(SpringExtension.class)
class CardRepositoryIntegrationTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSaveAndFindByCardNumber() {
        ClientEntity clientEntity = new ClientEntity(1l, "Joe", "12345678901", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");
        CardEntity cardEntity = new CardEntity(1l, "1234567890123456","12/23", "Credit","MAXCLUBCARD",
                clientEntity);
        clientRepository.save(clientEntity);
        cardRepository.save(cardEntity);
        CardEntity foundCard = cardRepository.findByCardNumber("1234567890123456");

        assertThat(foundCard).isNotNull();
        assertThat(foundCard.getCardNumber()).isEqualTo("1234567890123456");
        assertThat(foundCard.getClient().getDocument()).isEqualTo("12345678901");
    }

    @Test
    void testFindByCardNumberNotFound() {
        CardEntity foundCard = cardRepository.findByCardNumber("nonexistent");

        assertThat(foundCard).isNull();
    }
}