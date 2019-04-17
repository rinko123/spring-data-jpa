package com.rinko.repository

import com.rinko.entity.manytoone.Writer
import org.springframework.data.jpa.repository.JpaRepository

/**
 *create by liuliang on 2019/4/17.
 */
interface WriterRepository : JpaRepository<Writer, Long> {
}