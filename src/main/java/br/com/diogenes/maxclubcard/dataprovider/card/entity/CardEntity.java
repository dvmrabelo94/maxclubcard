package br.com.diogenes.maxclubcard.dataprovider.card.entity;

import br.com.diogenes.maxclubcard.dataprovider.client.entity.ClientEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true, length = 20)
    private String cardNumber;

    @Column(name = "expiration_date", nullable = false, length = 5)
    private String expirationDate;

    @Column(name = "type_card", nullable = false, length = 50)
    private String typeCard;

    @Column(name = "brand_card", nullable = false, length = 50)
    private String brandCard;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private ClientEntity client;
}