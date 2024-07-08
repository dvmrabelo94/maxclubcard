package br.com.diogenes.maxclubcard.dataprovider.luckynumber;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository.LuckyNumberRepository;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LuckyNumberGatewayImpl implements LuckyNumberGateway {

    public static final String EXCEPTION_MESSAGE = "M=insertAll, message=Error inserting all lucky number, exceptionMessage={}";
    private final LuckyNumberRepository luckyNumberRepository;

    public LuckyNumberGatewayImpl(LuckyNumberRepository luckyNumberRepository) {
        this.luckyNumberRepository = luckyNumberRepository;
    }

    @Override
    @Transactional
    public List<LuckyNumber> insertAll(List<LuckyNumberEntity> luckyNumberList, TransactionEntity transactionEntity) {
        log.info("M=insertAll, message=Start insert all lucky number, luckyNumberList={}, transactionEntity={}", luckyNumberList, transactionEntity);
        List<LuckyNumber> listLuckNumber = new ArrayList<>(List.of());
        luckyNumberRepository.saveAll(luckyNumberList)
                .forEach(luckyNumberEntity ->
                        listLuckNumber.add(LuckyNumberMapper.toDomain(luckyNumberEntity)));
        log.info("M=insertAll, message=Insert all lucky number with success, listLuckNumber={}", listLuckNumber);
        return listLuckNumber;

    }

    @Override
    public List<LuckyNumber> findByIsValid() {
        log.info("M=findByIsValid, message=Find all lucky number by is valid");
        var luckyNumberList = luckyNumberRepository.findByIsValid(true)
                .stream()
                .map(LuckyNumberMapper::toDomain)
                .collect(Collectors.toList());
        log.info("M=findByIsValid, message=Find all lucky number by is valid with success, luckyNumberList={}", luckyNumberList);
        return luckyNumberList;
    }

    @Override
    public void updateAllByLuckyNumbers(Boolean isValid, List<String> luckyNumber) {
        log.info("M=updateAllByLuckyNumbers, message=Update all lucky number by is valid, isValid={}, luckyNumber={}", isValid, luckyNumber);
        luckyNumberRepository.updateAllByLuckyNumbers(isValid, luckyNumber);
        log.info("M=updateAllByLuckyNumbers, message=Update all lucky number by is valid with success");
    }


}
