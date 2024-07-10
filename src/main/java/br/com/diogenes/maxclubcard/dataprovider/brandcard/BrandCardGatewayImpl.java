package br.com.diogenes.maxclubcard.dataprovider.brandcard;

import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.repository.BrandRepository;
import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandCardGatewayImpl implements BrandCardGateway {

    private final BrandRepository brandRepository;

    public BrandCardGatewayImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandEntity> findAllByIsActiveTrue() {
        try {
            return brandRepository.findAllByIsActiveTrue();
        } catch (EntityNotFoundException e) {
            log.error("Error finding all brands: {}", e.getMessage());
            throw new BusinessException("Error finding all brands: Entity not found.");
        }
    }

    public BrandEntity save(BrandEntity brandEntity) {
        try {
            return brandRepository.save(brandEntity);
        } catch (DataIntegrityViolationException e) {
            log.error("Error saving brand: {}", e.getMessage());
            throw new BusinessException("Error saving brand: Duplicate entry found.");
        }

    }
}
