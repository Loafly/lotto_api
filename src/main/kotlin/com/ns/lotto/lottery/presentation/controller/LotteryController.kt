package com.ns.lotto.lottery.presentation.controller

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.service.LotteryService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lotteries")
class LotteryController(private val lotteryService: LotteryService) {

    @GetMapping("/search")
//    fun lottery(): LotteryDto.SearchResponse? {
    fun lottery(): Page<Lottery> {
        return lotteryService.search();
    }

}