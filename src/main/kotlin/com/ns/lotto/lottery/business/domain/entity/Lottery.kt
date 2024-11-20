package com.ns.lotto.lottery.business.domain.entity

import com.ns.lotto.lottery.presentation.shceduler.LotteryApiDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
@Table(name = "lottery")
class Lottery (

    @Id
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "draw_date")
    val drawDate: LocalDate?,

    @Column(name = "winning_number_1")
    val winningNumber1: Int,

    @Column(name = "winning_number_2")
    val winningNumber2: Int,

    @Column(name = "winning_number_3")
    val winningNumber3: Int,

    @Column(name = "winning_number_4")
    val winningNumber4: Int,

    @Column(name = "winning_number_5")
    val winningNumber5: Int,

    @Column(name = "winning_number_6")
    val winningNumber6: Int,

    @Column(name = "bonus_number")
    val bonusNumber: Int,
) {
    constructor(dto: LotteryApiDto) : this(
        id = dto.drwNo,
        drawDate = LocalDate.parse(dto.drwNoDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        winningNumber1 = dto.drwtNo1,
        winningNumber2 = dto.drwtNo2,
        winningNumber3 = dto.drwtNo3,
        winningNumber4 = dto.drwtNo4,
        winningNumber5 = dto.drwtNo5,
        winningNumber6 = dto.drwtNo6,
        bonusNumber = dto.bnusNo
    )
}


