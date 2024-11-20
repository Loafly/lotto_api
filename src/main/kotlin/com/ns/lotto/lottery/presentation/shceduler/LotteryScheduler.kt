package com.ns.lotto.lottery.presentation.shceduler

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.repository.LotteryRepository
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class LotteryScheduler(private val lotteryRepository: LotteryRepository) {

    //    @Scheduled()
    fun updateLottery() {
        val restTemplate = RestTemplate()

        var 회차 = 1
        for (i in 1..1145) {
            회차 = i
            val url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + 회차
            val json = restTemplate.getForObject(url, String::class.java)
            val lotteryDto = json?.let { Json.decodeFromString<LotteryApiDto>(it) }

            lotteryDto?.let { Lottery(it) }?.let { lotteryRepository.save(it) }
            // 로또 번호 저장
        }




    }
}