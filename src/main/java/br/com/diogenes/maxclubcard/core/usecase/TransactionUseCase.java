package br.com.diogenes.maxclubcard.core.usecase;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transaction;
import br.com.diogenes.maxclubcard.core.domain.transaction.TransactionOut;

public interface TransactionUseCase {

    TransactionOut registerTransaction(Transaction transaction);
}
