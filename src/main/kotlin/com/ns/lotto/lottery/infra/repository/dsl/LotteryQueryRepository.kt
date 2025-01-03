package com.ns.lotto.lottery.infra.repository.dsl

import com.ns.lotto.lottery.business.domain.entity.Lottery
import com.ns.lotto.lottery.business.domain.entity.QLottery.lottery
import com.ns.lotto.lottery.presentation.controller.LotteryDto
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Wildcard
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class LotteryQueryRepository(private val jpaQueryFactory: JPAQueryFactory) {

    // 검색
    fun search(searchRequest: LotteryDto.SearchRequest, pageable: Pageable): Page<Lottery> {

        val content = jpaQueryFactory.selectFrom(lottery)
            .where(
                eqExistId(searchRequest.id)
            )
            .orderBy(*getSortOrders(pageable))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = jpaQueryFactory
            .select(Wildcard.count)
            .where(
                eqExistId(searchRequest.id)
            )
            .from(lottery)
            .fetchFirst()

        return PageImpl(content, pageable, total)
    }

    fun eqExistId(id: Long?): BooleanExpression? {
        if (id == null) {
            return null
        }

        return lottery.id.eq(id)
    }

    private fun getSortOrders(pageable: Pageable): Array<OrderSpecifier<*>> {
        if (!pageable.sort.isSorted) {
            return arrayOf<OrderSpecifier<*>>(lottery.id.desc()) // 기본 정렬 조건
        }

        // 여러 정렬 조건 처리
        val orders: MutableList<OrderSpecifier<*>> = ArrayList()
        for (order in pageable.sort) {
            val property = order.property
            val isAscending = order.isAscending

            val orderSpecifier: OrderSpecifier<*> = when (property) {
                "id" -> if (isAscending) lottery.id.asc() else lottery.id.desc()
                else -> lottery.id.desc()
            }
            orders.add(orderSpecifier)
        }

        return orders.toTypedArray<OrderSpecifier<*>>() // 배열로 변환하여 반환
    }
}