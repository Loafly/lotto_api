package com.ns.lotto.lottery.presentation.controller

import com.ns.lotto.lottery.business.domain.service.LotteryFacade
import com.ns.lotto.lottery.business.domain.service.LotteryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lotteries")
class LotteryController(private val lotteryService: LotteryService,
                        private val lotteryFacade: LotteryFacade) {

    @GetMapping("/search")
    fun lottery(searchRequest: LotteryDto.SearchRequest,
                pageable: Pageable): Page<LotteryDto>? {
        return lotteryService.search(searchRequest, pageable).map { lottery -> LotteryDto(lottery) };
    }

    @GetMapping("/latest")
    fun getLatest(): LotteryDto {
        return LotteryDto(lotteryService.getLatest());
    }

    @GetMapping("/frequent")
    fun getFrequentReferral(): LotteryDto.FrequentNumbersResponse {
        return lotteryFacade.getFrequent()
    }
}