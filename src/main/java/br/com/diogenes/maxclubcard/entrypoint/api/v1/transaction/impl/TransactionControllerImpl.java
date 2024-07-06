package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.impl;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.TransactionController;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionControllerImpl implements TransactionController {

    @Override
    public ResponseEntity<TransactionResponse> generateLuckyNumber(TransactionRequest transactionRequest) {
        return null;
    }
}
