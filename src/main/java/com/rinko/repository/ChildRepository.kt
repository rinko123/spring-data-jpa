package com.rinko.repository

import com.rinko.entity.my.Child
import org.springframework.data.jpa.repository.JpaRepository

/**
 *create by liuliang on 2018/12/7.
 */
interface ChildRepository : JpaRepository<Child, Long> {

    fun findByParentId(goodsId: Long?): List<Child>
}