package br.com.diogenes.maxclubcard.entrypoint.api.v1.card;

import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardRequest;
import br.com.diogenes.maxclubcard.entrypoint.api.v1.card.dto.CardResponse;
import br.com.diogenes.maxclubcard.entrypoint.exception.model.ErrorResponseDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CardController", description = "Serviço responsável por cadastrar os cartões na promoção.")
public interface CardController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardResponse.class))),
            @ApiResponse(responseCode = "400, 403, 404", description = "Requisição inválida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDomain.class))),
            @ApiResponse(responseCode = "500, 503", description = "Requisição indisponível",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDomain.class)))})
    @Operation(
            summary = "Serviço responsável por cadastrar e habilitar cartão de crédito",
            description = "<u>\t<li>Para participar da promoção é necessário cadastrar e habilitar o cartão MaxClubCard.</li>\n\t</u>"
    )
    @PostMapping("v1/cards")
    ResponseEntity<CardResponse> registerCard(
            @RequestBody CardRequest cardRequest
    );
}
