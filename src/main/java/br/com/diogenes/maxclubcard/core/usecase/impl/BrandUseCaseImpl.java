package br.com.diogenes.maxclubcard.core.usecase.impl;

import br.com.diogenes.maxclubcard.core.gateway.BrandCardGateway;
import br.com.diogenes.maxclubcard.core.usecase.BrandUseCase;
import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandUseCaseImpl implements BrandUseCase {

    private final BrandCardGateway brandCardGateway;

    public BrandUseCaseImpl(BrandCardGateway brandCardGateway) {
        this.brandCardGateway = brandCardGateway;
    }

    public List<BrandEntity> findAllByIsActiveTrue() {
        return brandCardGateway.findAllByIsActiveTrue();
    }

    public BrandEntity save(BrandEntity brandEntity) {
        return brandCardGateway.save(brandEntity);
    }
}
