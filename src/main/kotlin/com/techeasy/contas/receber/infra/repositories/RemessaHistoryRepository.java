package com.techeasy.contas.receber.infra.repositories;

import com.techeasy.contas.receber.domain.remessa.model.RemessaHistory;
import com.techeasy.contas.receber.domain.remessa.model.RemessaHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemessaHistoryRepository extends JpaRepository<RemessaHistory, Long> {

    @Query(value = "select rh.id, rh.name, rh.created_at as createdAt from arquivos_remessas_history rh order by created_at desc", nativeQuery = true)
    List<RemessaHistoryDTO> listAllWithoutBlob();
}
