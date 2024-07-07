package br.com.diogenes.maxclubcard.core.domain.transaction;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;

import java.util.List;

public record TransactionOut(
        List<LuckyNumber> luckyNumberList
) {
}
