package br.com.diogenes.maxclubcard.dataprovider.transaction;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionGatewayImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardGateway cardGateway;

    @Mock
    private LuckyNumberGateway luckyNumberGateway;

    @InjectMocks
    private TransactionGatewayImpl transactionGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRegisterTransactionInRepositoryWhenRegisterTransactionIsCalled() {
        ClientEntity clientEntity = new ClientEntity(1L, "Diogenes", "12312312312", LocalDate.now(), "Masculino", "mail@mail.com", "77112234455");
        CardEntity cardEntity = new CardEntity(1L, "1234", "12/99", "Credt", "MAXCLUBCARD", clientEntity);
        Transaction transaction = new Transaction("1234", "1234123412341234", BigDecimal.valueOf(300.00), LocalDateTime.now());
        TransactionEntity transactionEntity = new TransactionEntity(1L, LocalDateTime.now(), "1234", BigDecimal.valueOf(300.00), cardEntity);

        when(cardGateway.getCardEntity(anyString())).thenReturn(cardEntity);
        when(transactionRepository.save(any())).thenReturn(transactionEntity);

        transactionGateway.registerTransaction(transaction);

        verify(transactionRepository).save(any());
    }
}