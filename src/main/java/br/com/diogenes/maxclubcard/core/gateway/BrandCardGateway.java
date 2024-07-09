package br.com.diogenes.maxclubcard.core.gateway;

import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;

import java.util.List;

public interface BrandCardGateway {

    List<BrandEntity> findAllByIsActiveTrue();

    BrandEntity save(BrandEntity brandEntity);
}
