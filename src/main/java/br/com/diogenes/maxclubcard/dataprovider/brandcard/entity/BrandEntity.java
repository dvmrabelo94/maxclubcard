package br.com.diogenes.maxclubcard.dataprovider.brandcard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false, unique = true, length = 36)
    private String brandName;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;
}
