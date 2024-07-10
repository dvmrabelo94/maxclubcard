package br.com.diogenes.maxclubcard.dataprovider.brandcard.repository;

import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BrandRepository extends CrudRepository<BrandEntity, Long> {

    @Modifying
    @Query("select b from BrandEntity b where b.isActive = true")
    List<BrandEntity> findAllByIsActiveTrue();
}
