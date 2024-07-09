package br.com.diogenes.maxclubcard.dataprovider.luckynumber.entity;

import br.com.diogenes.maxclubcard.dataprovider.transaction.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lucky_number")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LuckyNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lucky_number", nullable = false, unique = true, length = 36)
    private String luckyNumber;

    @Column(name = "is_valid", nullable = false, columnDefinition = "boolean default true")
    private boolean isValid;

    @ManyToOne
    @JoinColumn(name = "id_transaction", nullable = false)
    private TransactionEntity transaction;
}