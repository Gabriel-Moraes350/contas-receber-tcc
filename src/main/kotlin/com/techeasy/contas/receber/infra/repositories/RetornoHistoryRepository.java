package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.retorno.model.RetornoHistory;
import com.techeasy.contas.receber.domain.retorno.model.RetornoHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetornoHistoryRepository extends JpaRepository<RetornoHistory, Long> {

    @Query(value = "select rh.id, rh.name, rh.created_at as createdAt from arquivos_retorno_history rh order by created_at desc", nativeQuery = true)
    List<RetornoHistoryDTO> listAllWithoutBlob();
}
