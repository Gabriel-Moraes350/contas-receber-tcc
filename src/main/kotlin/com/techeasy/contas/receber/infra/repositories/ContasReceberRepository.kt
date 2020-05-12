package com.techeasy.contas.receber.infra.repositories

import com.techeasy.contas.receber.domain.contasreceber.model.ContasReceber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContasReceberRepository : JpaRepository<ContasReceber, Long>