package com.ns.lotto.lottery.presentation.shceduler

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.service.LotteryService
import kotlinx.serialization.json.Json
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LotteryScheduler(private val lotteryService: LotteryService
) {
//    @Scheduled(cron = "0 * 20-21 ? * SAT")
    @Scheduled(cron = "0 0/10 20-21 ? * SAT")
    fun updateLottery() {
        val restTemplate = RestTemplate()

        val latest = lotteryService.getLatest()
        val latestId = latest.id?.plus(1L)

        val url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + latestId
        val json = restTemplate.getForObject(url, String::class.java)

        val objectMapper = jacksonObjectMapper()
        val root: JsonNode = objectMapper.readTree(json)
        val returnValue = root["returnValue"]?.asText() // "fail" 값 가져오기

        // 실패인 경우
        if (returnValue == "fail") {
            print("fail이야!!")
            return
        }

        val lotteryDto = json?.let { Json.decodeFromString<LotteryApiDto>(it) }
        lotteryDto?.let { Lottery(it) }?.let { lotteryService.save(it) }

    }
}