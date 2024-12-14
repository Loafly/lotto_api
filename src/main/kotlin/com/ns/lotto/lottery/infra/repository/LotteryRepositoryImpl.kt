package com.ns.lotto.lottery.infra.repository

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.repository.LotteryRepository
import com.ns.lotto.lottery.infra.repository.dsl.LotteryQueryRepository
import com.ns.lotto.lottery.infra.repository.jpa.LotteryJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class LotteryRepositoryImpl(private val lotteryJpaRepository: LotteryJpaRepository,
                            private val lotteryQueryRepository: LotteryQueryRepository
) : LotteryRepository {

    override fun save(lottery: Lottery): Lottery {
        return lotteryJpaRepository.save(lottery)
    }

    @Transactional(readOnly = true)
    override fun search(pageable: Pageable): Page<Lottery> {
        return lotteryQueryRepository.search(pageable)
    }

    override fun getTopByOrderByIdDesc(): Lottery {
        return lotteryJpaRepository.getTopByOrderByIdDesc()
    }

    override fun findAll(): List<Lottery> {
        return lotteryJpaRepository.findAll()
    }
}