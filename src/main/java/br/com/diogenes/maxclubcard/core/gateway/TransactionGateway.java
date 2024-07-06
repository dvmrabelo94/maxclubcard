package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.core.domain.transaction.Transanction;

public interface TransactionGateway {

    void registerTransaction(Transanction transaction);
}
