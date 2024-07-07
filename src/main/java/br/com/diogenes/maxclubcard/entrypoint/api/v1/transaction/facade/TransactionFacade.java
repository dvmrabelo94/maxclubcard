package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.facade;

import br.com.diogenes.maxclubcard.core.usecase.TransactionUseCase;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionResponse;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.mapper.TransactionMapperEntrypoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionFacade {

    private final TransactionUseCase transactionUseCase;

    TransactionFacade(final TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    public TransactionResponse registerTransaction(TransactionRequest transactionRequest) {
        var transaction = TransactionMapperEntrypoint.toTransaction(transactionRequest);
        return TransactionMapperEntrypoint.toTransactionResponse(
                transactionUseCase.registerTransaction(transaction));
    }
}
