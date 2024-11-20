package com.ns.lotto.lottery.presentation.controller

import java.time.LocalDate

class LotteryDto(

) {


    data class SearchResponse(
        val id: Long,
        val winningNumber1: Int,
        val winningNumber2: Int,
        val winningNumber3: Int,
        val winningNumber4: Int,
        val winningNumber5: Int,
        val winningNumber6: Int,
        val bonusNumber: Int,
        val drawDate: LocalDate
    )
}