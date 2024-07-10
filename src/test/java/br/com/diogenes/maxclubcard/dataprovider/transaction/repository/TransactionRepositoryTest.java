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
        ClientEntity clientEntity =  ClientEntity.builder().name("Joe")
                .document("12345678901")
                .birth(LocalDate.of(1990, 1, 1))
                .gender("Masculino")
                .email("joe@mail.com")
                .phoneNumber("77112234455").build();
        clientRepository.save(clientEntity);
        CardEntity cardEntity =  CardEntity.builder()
                .cardNumber("1234567890123456")
                .expirationDate("12/99")
                .typeCard("Credit")
                .brandCard("MAXCLUBCARD")
                .client(clientEntity).build();
        cardRepository.save(cardEntity);


        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionDate(LocalDateTime.now())
                .transactionNumber("1234")
                .value(BigDecimal.valueOf(100.0))
                .card(cardEntity).build();

        transactionRepository.save(transactionEntity);

        TransactionEntity foundTransaction = transactionRepository.findAll().iterator().next();

        assertNotNull(foundTransaction);
    }
}