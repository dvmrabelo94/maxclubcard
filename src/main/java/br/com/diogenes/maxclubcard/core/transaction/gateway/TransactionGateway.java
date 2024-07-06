package br.com.diogenes.maxclubcard.core.transaction.gateway;

import br.com.diogenes.maxclubcard.core.transaction.domain.Transanction;

public interface TransactionGateway {

    void registerTransaction(Transanction transaction);
}
