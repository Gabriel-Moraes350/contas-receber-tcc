package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.remessa.model.RemessaContasReceberId;
import com.techeasy.contas.receber.domain.remessa.model.RemessaContasReceberRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemessaContasReceberRelationRepository extends JpaRepository<RemessaContasReceberRelation, RemessaContasReceberId> {

    @Query(value = "select * from remessa_contas_receber where remessa_id = :id", nativeQuery= true)
    List<RemessaContasReceberRelation> findByRemessaId(@Param("id") long id);
}
