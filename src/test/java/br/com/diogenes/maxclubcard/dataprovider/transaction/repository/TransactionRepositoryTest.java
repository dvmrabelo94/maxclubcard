package br.com.diogenes.maxclubcard.dataprovider.transaction.repository;

import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = "spring.config.name=application-test")
@ExtendWith(SpringExtension.class)
class TransactionRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testSaveAndFindByTransactionNumber() {
        ClientEntity clientEntity = new ClientEntity(1l, "Joe", "12345678901", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");
        CardEntity cardEntity = new CardEntity(1l, "1234567890123456","12/23", "Credit","MAXCLUBCARD",
                clientEntity);
        TransactionEntity transactionEntity = new TransactionEntity(1l, LocalDateTime.now(), "1234", BigDecimal.valueOf(100.0), cardEntity);

        clientRepository.save(clientEntity);
        cardRepository.save(cardEntity);
        transactionRepository.save(transactionEntity);

        TransactionEntity foundTransaction = transactionRepository.findById(1l).get();

        assertNotNull(foundTransaction);
    }
}