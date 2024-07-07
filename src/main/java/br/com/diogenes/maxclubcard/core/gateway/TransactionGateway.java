package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;

public interface TransactionGateway {

    TransactionOut registerTransaction(Transaction transaction);
}
