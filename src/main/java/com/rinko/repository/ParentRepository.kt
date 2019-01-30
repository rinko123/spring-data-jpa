package com.rinko.repository

import com.rinko.entity.my.Parent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 *create by liuliang on 2018/12/7.
 */
interface ParentRepository : JpaRepository<Parent, Long>, JpaSpecificationExecutor<Parent> {
}