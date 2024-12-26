package com.ns.lotto.lottery.business.domain.service

import com.ns.lotto.lottery.presentation.controller.LotteryDto
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class LotteryFacade(private val lotteryService: LotteryService) {

    @Transactional(readOnly = true)
    fun getFrequent(): LotteryDto.FrequentNumbersResponse {
        // 가장 많이 나온 번호
        val mostFrequentNumbers = lotteryService.getMostFrequentNumbers()
        // 가장 적게 나온 번호
        val leastFrequentNumbers = lotteryService.getLeastFrequentNumbers()
        // 번호 출현 빈도수 그래프
        val frequentNumbers = lotteryService.getFrequentNumbers()

        return LotteryDto.FrequentNumbersResponse(
            mostFrequentNumbers = LotteryDto.Referral(mostFrequentNumbers),
            leastFrequentNumbers = LotteryDto.Referral(leastFrequentNumbers),
            frequentNumbers = frequentNumbers
        )
    }

}