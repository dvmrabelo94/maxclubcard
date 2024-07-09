package br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository;

import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.card.repository.CardRepository;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.repository.ClientRepository;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.config.name=application-test")
@ExtendWith(SpringExtension.class)
class LuckyNumberRepositoryTest {

    @Autowired
    private LuckyNumberRepository luckyNumberRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @Transactional
    void testUpdateAllByLuckyNumbers() {
        ClientEntity clientEntity = new ClientEntity(1l, "Joe", "12345678901", LocalDate.of(1990, 1, 1), "Masculino", "joe@mail.com", "77112234455");
        CardEntity cardEntity = new CardEntity(1l, "1234567890123456","12/23", "Credit","MAXCLUBCARD",
                clientEntity);
        TransactionEntity transactionEntity = new TransactionEntity(1l, LocalDateTime.now(), "1234", BigDecimal.valueOf(100.0), cardEntity);

        clientRepository.save(clientEntity);
        cardRepository.save(cardEntity);
        transactionRepository.save(transactionEntity);

        LuckyNumberEntity luckyNumber1 = new LuckyNumberEntity();
        luckyNumber1.setLuckyNumber("12345");
        luckyNumber1.setValid(true);
        luckyNumber1.setTransaction(transactionEntity);

        LuckyNumberEntity luckyNumber2 = new LuckyNumberEntity();
        luckyNumber2.setLuckyNumber("67890");
        luckyNumber2.setValid(false);
        luckyNumber2.setTransaction(transactionEntity);

        luckyNumberRepository.save(luckyNumber1);
        luckyNumberRepository.save(luckyNumber2);

        luckyNumberRepository.updateAllByLuckyNumbers(false, List.of("12345"));

        List<LuckyNumberEntity> validNumbers = luckyNumberRepository.findByIsValid(false);
        assertThat(validNumbers).hasSize(2);
        assertThat(validNumbers.stream().anyMatch(l -> l.getLuckyNumber().equals("12345"))).isTrue();
        assertThat(validNumbers.stream().anyMatch(l -> l.getLuckyNumber().equals("67890"))).isTrue();
    }

}