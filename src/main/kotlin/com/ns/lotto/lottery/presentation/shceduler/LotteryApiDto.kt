package com.ns.lotto.lottery.presentation.shceduler

import kotlinx.serialization.Serializable

@Serializable
class LotteryApiDto(
    val totSellamnt: Long,
    val returnValue: String,
    val drwNoDate: String,
    val firstWinamnt: Long,
    val drwtNo6: Int,
    val drwtNo4: Int,
    val firstPrzwnerCo: Int,
    val drwtNo5: Int,
    val bnusNo: Int,
    val firstAccumamnt: Long,
    val drwNo: Long,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo1: Int
)