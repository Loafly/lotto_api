package com.ns.lotto.lottery.business.domain.service

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.repository.LotteryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LotteryService(private val lotteryRepository: LotteryRepository) {

    // 동적 조회
    @Transactional(readOnly = true)
    fun search(): Page<Lottery> {
        return lotteryRepository.search(PageRequest.of(0, 10))
    }

    // 가장 최근 데이터 조회
    @Transactional(readOnly = true)
    fun getLatest(): Lottery {
        return lotteryRepository.getTopByOrderByIdDesc();
    }

    // 데이터 저장
    @Transactional
    fun save(lottery: Lottery): Lottery {
        return lotteryRepository.save(lottery)
    }

    fun getMostFrequentNumbers(): Lottery {
        val lotteries = lotteryRepository.findAll()

        // 모든 일반 번호와 보너스 번호 추출
        val allNumbers = lotteries.flatMap {
            listOf(it.winningNumber1, it.winningNumber2, it.winningNumber3, it.winningNumber4, it.winningNumber5, it.winningNumber6)
        }
        val allBonusNumbers = lotteries.map { it.bonusNumber }

        // 일반 번호 빈도 계산 및 상위 6개 추출
        val numberFrequency = allNumbers.groupingBy { it }.eachCount()
        val recommendedNumbers = numberFrequency.entries
            .sortedByDescending { it.value } // 빈도순 정렬
            .take(6) // 상위 6개 선택
            .map { it.key }

        // 보너스 번호 빈도 계산 및 최빈값 선택
        val bonusFrequency = allBonusNumbers.groupingBy { it }.eachCount()
        val recommendedBonus = bonusFrequency.entries
            .maxByOrNull { it.value } // 가장 많이 나온 보너스 번호 선택
            ?.key ?: 0 // 보너스 번호가 없으면 0 반환

        // Lottery 객체로 추천 번호 생성
        return Lottery(
            winningNumber1 = recommendedNumbers[0],
            winningNumber2 = recommendedNumbers[1],
            winningNumber3 = recommendedNumbers[2],
            winningNumber4 = recommendedNumbers[3],
            winningNumber5 = recommendedNumbers[4],
            winningNumber6 = recommendedNumbers[5],
            bonusNumber = recommendedBonus
        )
    }

    fun getLeastFrequentNumbers(): Lottery {
        val lotteries = lotteryRepository.findAll()

        // 모든 일반 번호와 보너스 번호 추출
        val allNumbers = lotteries.flatMap {
            listOf(it.winningNumber1, it.winningNumber2, it.winningNumber3, it.winningNumber4, it.winningNumber5, it.winningNumber6)
        }
        val allBonusNumbers = lotteries.map { it.bonusNumber }

        // 일반 번호 빈도 계산 및 상위 6개 추출
        val numberFrequency = allNumbers.groupingBy { it }.eachCount()
        val recommendedNumbers = numberFrequency.entries
            .sortedBy { it.value } // 빈도순 정렬
            .take(6) // 상위 6개 선택
            .map { it.key }

        // 보너스 번호 빈도 계산 및 최빈값 선택
        val bonusFrequency = allBonusNumbers.groupingBy { it }.eachCount()
        val recommendedBonus = bonusFrequency.entries
            .minByOrNull { it.value } // 가장 적게 나온 보너스 번호 선택
            ?.key ?: 0 // 보너스 번호가 없으면 0 반환

        // Lottery 객체로 추천 번호 생성
        return Lottery(
            winningNumber1 = recommendedNumbers[0],
            winningNumber2 = recommendedNumbers[1],
            winningNumber3 = recommendedNumbers[2],
            winningNumber4 = recommendedNumbers[3],
            winningNumber5 = recommendedNumbers[4],
            winningNumber6 = recommendedNumbers[5],
            bonusNumber = recommendedBonus
        )
    }

    fun getFrequentNumbers(): Map<Int, Int> {
        val lotteries = lotteryRepository.findAll()

        // 모든 일반 번호와 보너스 번호 추출
        val allNumbers = lotteries.flatMap {
            listOf(it.winningNumber1, it.winningNumber2, it.winningNumber3, it.winningNumber4, it.winningNumber5, it.winningNumber6)
        }

        // 일반 번호 빈도 계산 및 상위 6개 추출
        return allNumbers
            .groupingBy { it }
            .eachCount()
            .map { it.key to it.value }
            .sortedBy { it.first }
            .toMap()

    }
}