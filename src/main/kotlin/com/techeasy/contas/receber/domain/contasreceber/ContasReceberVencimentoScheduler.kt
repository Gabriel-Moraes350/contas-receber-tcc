package com.techeasy.contas.receber.domain.contasreceber

import com.techeasy.contas.receber.infra.repositories.ContasReceberRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.transaction.Transactional


@EnableAsync
@Component
class ContasReceberVencimentoScheduler(val contasReceberRepo: ContasReceberRepository) {

    companion object{
        @JvmStatic
        private val log = LoggerFactory.getLogger(ContasReceberVencimentoScheduler.javaClass)
    }

    @Async
    @Scheduled(fixedRate = 60000)
    @Transactional
    fun updateContasVencidas() {
        log.info("Atualizando contas vencidas")
        contasReceberRepo.updateStatusVencidas()
    }
}