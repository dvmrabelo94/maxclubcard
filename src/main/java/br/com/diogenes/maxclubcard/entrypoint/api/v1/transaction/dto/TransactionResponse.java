package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto;

import br.com.diogenes.maxclubcard.core.domain.luckynumber.LuckyNumber;

import java.util.List;

public record TransactionResponse(
        List<LuckyNumber> luckyNumberList
) {
}
