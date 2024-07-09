package br.com.diogenes.maxclubcard.dataprovider.transaction;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.gateway.CardGateway;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import br.com.diogenes.maxclubcard.dataprovider.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
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
        Transaction transaction = new Transaction("1234", "1234123412341234", BigDecimal.valueOf(300.00), LocalDateTime.now());
        TransactionEntity transactionEntity = new TransactionEntity(1L, LocalDateTime.now(), "1234", BigDecimal.valueOf(300.00), null);

        when(transactionRepository.save(any())).thenReturn(transactionEntity);

        transactionGateway.registerTransaction(transaction);

        verify(transactionRepository).save(any());
    }
}