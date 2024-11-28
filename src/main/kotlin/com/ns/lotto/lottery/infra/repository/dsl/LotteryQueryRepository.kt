package com.ns.lotto.lottery.infra.repository.dsl

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.entity.QLottery
import com.querydsl.core.types.dsl.Wildcard
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class LotteryQueryRepository(private val jpaQueryFactory: JPAQueryFactory) {

    // 검색
    fun search(pageable: Pageable): Page<Lottery> {

        val content = jpaQueryFactory.selectFrom(QLottery.lottery)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = jpaQueryFactory
            .select(Wildcard.count)
            .from(QLottery.lottery)
            .fetchFirst()

        return PageImpl(content, pageable, total)
    }
}