package br.com.diogenes.maxclubcard.dataprovider.transaction.entity;

import br.com.diogenes.maxclubcard.dataprovider.card.entity.CardEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "transaction_number", nullable = false, length = 50)
    private String transactionNumber;

    @Column(name = "transaction_value", nullable = false, precision = 19, scale = 4)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private CardEntity card;
}