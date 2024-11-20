package com.ns.lotto.lottery.business.domain.service

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.repository.LotteryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class LotteryService(private val lotteryRepository: LotteryRepository) {

    // 동적 조회
    fun search(): Page<Lottery> {

        return lotteryRepository.search(PageRequest.of(0, 10))
    }

}