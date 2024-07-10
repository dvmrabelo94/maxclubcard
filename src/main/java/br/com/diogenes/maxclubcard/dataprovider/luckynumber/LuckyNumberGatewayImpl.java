package br.com.diogenes.maxclubcard.dataprovider.luckynumber;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository.LuckyNumberRepository;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LuckyNumberGatewayImpl implements LuckyNumberGateway {

    private final LuckyNumberRepository luckyNumberRepository;

    public LuckyNumberGatewayImpl(LuckyNumberRepository luckyNumberRepository) {
        this.luckyNumberRepository = luckyNumberRepository;
    }

    @Override
    @Transactional
    public List<LuckyNumber> insertAll(List<LuckyNumberEntity> luckyNumberList, TransactionEntity transactionEntity) {
        try {
            log.info("M=insertAll, message=Start insert all lucky number, luckyNumberList={}, transactionEntity={}", luckyNumberList, transactionEntity);
            List<LuckyNumber> listLuckNumber = new ArrayList<>(List.of());
            luckyNumberRepository.saveAll(luckyNumberList)
                    .forEach(luckyNumberEntity ->
                            listLuckNumber.add(LuckyNumberMapper.toDomain(luckyNumberEntity)));
            log.info("M=insertAll, message=Insert all lucky number with success, listLuckNumber={}", listLuckNumber);
            return listLuckNumber;
        } catch (DataIntegrityViolationException e) {
            log.error("M=insertAll, message=Error inserting all lucky number, exceptionMessage={}", e.getMessage());
            throw new DataIntegrityViolationException("Error inserting all lucky number: Duplicate entry found.");
        } catch (Exception e) {
            log.error("M=insertAll, message=Error inserting all lucky number, exceptionMessage={}", e.getMessage());
            throw new RuntimeException("Error inserting all lucky number: " + e.getMessage());
        }


    }

    @Override
    public List<LuckyNumber> findByIsValid() {
        log.info("M=findByIsValid, message=Find all lucky number by is valid");
        var luckyNumberList = luckyNumberRepository.findByIsValid(true)
                .stream()
                .map(LuckyNumberMapper::toDomain)
                .collect(Collectors.toList());
        log.info("M=findByIsValid, message=Find all lucky number by is valid with success, luckyNumberList={}", luckyNumberList);
        if (luckyNumberList.isEmpty()) {
            log.error("M=findByIsValid, message=Error finding all lucky number by is valid, exceptionMessage=Lucky number not found");
            throw new EntityNotFoundException("Lucky number not found");
        }
        return luckyNumberList;
    }

    @Override
    public void updateAllByLuckyNumbers(Boolean isValid, List<String> luckyNumber) {
        log.info("M=updateAllByLuckyNumbers, message=Update all lucky number by is valid, isValid={}, luckyNumber={}", isValid, luckyNumber);
        try {
            luckyNumberRepository.updateAllByLuckyNumbers(isValid, luckyNumber);
        } catch (Exception e) {
            log.error("M=updateAllByLuckyNumbers, message=Error updating all lucky number by is valid, exceptionMessage={}", e.getMessage());
            throw new RuntimeException("Error updating all lucky number by is valid: " + e.getMessage());
        }
        log.info("M=updateAllByLuckyNumbers, message=Update all lucky number by is valid with success");
    }


}
