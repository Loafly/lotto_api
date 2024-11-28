package com.ns.lotto.lottery.infra.repository.jpa

import com.ns.lotto.lottery.business.domain.entity.Lottery
import org.springframework.data.jpa.repository.JpaRepository

interface LotteryJpaRepository: JpaRepository<Lottery, Long> {

    fun getTopByOrderByIdDesc(): Lottery
}