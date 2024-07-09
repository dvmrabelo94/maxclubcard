package br.com.diogenes.maxclubcard.dataprovider.luckynumber;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository.LuckyNumberRepository;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LuckyNumberGatewayImplTest {

    @Mock
    private LuckyNumberRepository luckyNumberRepository;

    @InjectMocks
    private LuckyNumberGatewayImpl luckyNumberGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallInsertAllInRepositoryWhenInsertAllIsCalled() {
        TransactionEntity transactionEntity = new TransactionEntity(1L,  LocalDateTime.now(), "1234", BigDecimal.valueOf(300.00), null);
        LuckyNumberEntity luckyNumberEntity = new LuckyNumberEntity(1L, "1234", true, transactionEntity);
        List<LuckyNumberEntity> luckyNumberEntityList = Arrays.asList(luckyNumberEntity);

        when(luckyNumberRepository.saveAll(any())).thenReturn(luckyNumberEntityList);

        luckyNumberGateway.insertAll(luckyNumberEntityList, transactionEntity);

        verify(luckyNumberRepository).saveAll(any());
    }

    @Test
    void shouldReturnLuckyNumberListWhenFindByIsValidIsCalled() {
        TransactionEntity transactionEntity = new TransactionEntity(1L,  LocalDateTime.now(), "1234", BigDecimal.valueOf(300.00), null);
        LuckyNumberEntity luckyNumberEntity = new LuckyNumberEntity(1L, "1234", true, transactionEntity);
        List<LuckyNumberEntity> luckyNumberEntityList = Arrays.asList(luckyNumberEntity);
        LuckyNumber luckyNumber = LuckyNumberMapper.toDomain(luckyNumberEntity);
        List<LuckyNumber> luckyNumberList = Arrays.asList(luckyNumber);

        when(luckyNumberRepository.findByIsValid(any())).thenReturn(luckyNumberEntityList);

        List<LuckyNumber> result = luckyNumberGateway.findByIsValid();

        assertEquals(luckyNumberList, result);
    }

    @Test
    void shouldCallUpdateAllByLuckyNumbersInRepositoryWhenUpdateAllByLuckyNumbersIsCalled() {
        List<String> luckyNumberList = Arrays.asList("1234");

        luckyNumberGateway.updateAllByLuckyNumbers(true, luckyNumberList);

        verify(luckyNumberRepository).updateAllByLuckyNumbers(any(), any());
    }
}