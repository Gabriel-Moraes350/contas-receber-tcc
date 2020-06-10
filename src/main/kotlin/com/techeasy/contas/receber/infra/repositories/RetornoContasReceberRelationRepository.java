package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.retorno.model.RetornoContasReceberId;
import com.techeasy.contas.receber.domain.retorno.model.RetornoContasReceberRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetornoContasReceberRelationRepository extends JpaRepository<RetornoContasReceberRelation, RetornoContasReceberId> {

    @Query(value = "select * from retorno_contas_receber where retorno_id = :id", nativeQuery = true)
    List<RetornoContasReceberRelation> findByContaReceberId(@Param("id") long id);
}
