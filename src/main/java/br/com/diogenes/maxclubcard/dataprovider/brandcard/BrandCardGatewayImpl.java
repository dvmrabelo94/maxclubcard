package br.com.diogenes.maxclubcard.dataprovider.brandcard;

import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;
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
        return brandRepository.findAllByIsActiveTrue();
    }

    public BrandEntity save(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }
}
