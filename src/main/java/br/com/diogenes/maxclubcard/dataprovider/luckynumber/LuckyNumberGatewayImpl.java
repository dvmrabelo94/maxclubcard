package br.com.diogenes.maxclubcard.dataprovider.luckynumber;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;
import br.com.diogenes.maxclubcard.core.gateway.LuckyNumberGateway;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity.LuckyNumberEntity;
import br.com.diogenes.maxclubcard.dataprovider.luckynumber.repository.LuckyNumberRepository;
import br.com.diogenes.maxclubcard.dataprovider.mapper.LuckyNumberMapper;
import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import lombok.extern.slf4j.Slf4j;
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
    public List<LuckyNumber> insertAll(List<LuckyNumberEntity> luckyNumberList, TransactionEntity transactionEntity) {
        List<LuckyNumber> listLuckNumber = new ArrayList<>(List.of());
        luckyNumberRepository.saveAll(luckyNumberList)
                .forEach(luckyNumberEntity ->
                        listLuckNumber.add(LuckyNumberMapper.toDomain(luckyNumberEntity)));
        return listLuckNumber;
    }

    @Override
    public List<LuckyNumber> findByIsValid() {
        return luckyNumberRepository.findByIsValid(true)
                .stream()
                .map(LuckyNumberMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAllByLuckyNumbers(Boolean isValid, List<String> luckyNumber) {
        luckyNumberRepository.updateAllByLuckyNumbers(isValid, luckyNumber);
    }


}
