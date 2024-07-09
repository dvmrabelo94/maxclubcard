package br.com.diogenes.maxclubcard.dataprovider.brandcard.repository;

import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {

    List<BrandEntity> findAllByIsActiveTrue();
}
