package com.ns.lotto.lottery.business.domain.repository

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.presentation.controller.LotteryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LotteryRepository {

    fun save(lottery: Lottery):Lottery
    fun search(searchRequest: LotteryDto.SearchRequest, pageable: Pageable): Page<Lottery>

    fun getTopByOrderByIdDesc(): Lottery
    fun findAll(): List<Lottery>
}