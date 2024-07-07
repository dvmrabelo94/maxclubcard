package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.mapper;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionResponse;

public class TransactionMapperEntrypoint {

    public static Transaction toTransaction(TransactionRequest transactionRequest) {
        return new Transaction(
                transactionRequest.transactionNumber(),
                transactionRequest.cardNumber(),
                transactionRequest.value(),
                transactionRequest.transactionDate()
        );
    }

    public static TransactionResponse toTransactionResponse(TransactionOut transaction) {
        return new TransactionResponse(
                transaction.luckyNumberList()
        );
    }
}
