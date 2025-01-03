package com.ns.lotto.lottery.presentation.controller

import com.ns.lotto.lottery.business.domain.entity.Lottery
import java.time.LocalDate


class LotteryDto(
    val id: Long?,
    val winningNumber1: Int,
    val winningNumber2: Int,
    val winningNumber3: Int,
    val winningNumber4: Int,
    val winningNumber5: Int,
    val winningNumber6: Int,
    val bonusNumber: Int,
    val drawDate: LocalDate?
) {
    constructor(lottery: Lottery) : this(
        lottery.id,
        lottery.winningNumber1,
        lottery.winningNumber2,
        lottery.winningNumber3,
        lottery.winningNumber4,
        lottery.winningNumber5,
        lottery.winningNumber6,
        lottery.bonusNumber,
        lottery.drawDate
    )

    data class SearchRequest(
        val id: Long?
    )


    data class SearchResponse(
        val id: Long,
        val winningNumber1: Int,
        val winningNumber2: Int,
        val winningNumber3: Int,
        val winningNumber4: Int,
        val winningNumber5: Int,
        val winningNumber6: Int,
        val bonusNumber: Int,
        val drawDate: LocalDate?,
        val firstWinAmount: Long
    ) {
        constructor(lottery: Lottery) : this(
            lottery.id!!,
            lottery.winningNumber1,
            lottery.winningNumber2,
            lottery.winningNumber3,
            lottery.winningNumber4,
            lottery.winningNumber5,
            lottery.winningNumber6,
            lottery.bonusNumber,
            lottery.drawDate,
            lottery.firstWinAmount!!
        )
    }

    data class FrequentNumbersResponse (val mostFrequentNumbers: Referral,
                                        val leastFrequentNumbers: Referral,
                                        val frequentNumbers: Map<Int, Int>)

    data class Referral (
        val winningNumber1: Int,
        val winningNumber2: Int,
        val winningNumber3: Int,
        val winningNumber4: Int,
        val winningNumber5: Int,
        val winningNumber6: Int,
    ) {
        constructor(lottery: Lottery) : this(
            lottery.winningNumber1,
            lottery.winningNumber2,
            lottery.winningNumber3,
            lottery.winningNumber4,
            lottery.winningNumber5,
            lottery.winningNumber6,
        )
    }
}