package br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.client.dto.ClientRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.transaction.dto.TransactionResponse;
import br.com.diogenes.maxclubcard.entrypoint.exception.model.ErrorResponseDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "TransactionController", description = "Serviço processar as transações e gerar os números da sorte.")
public interface TransactionController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400, 403, 404", description = "Requisição inválida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDomain.class))),
            @ApiResponse(responseCode = "500, 503", description = "Requisição indisponível",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDomain.class)))})
    @Operation(
            summary = "Serviço responsável por gerar os números da sorte",
            description = "<u>\t<li>Para participar gerar um número da sorte o cartão e cliente precisam estar cadastrados e a transação ser maior que R$150,00.</li>\n\t</u>"
    )
    @PostMapping("v1/transactions")
    ResponseEntity<TransactionResponse> generateLuckyNumber(
            @RequestBody TransactionRequest transactionRequest
    );
}
