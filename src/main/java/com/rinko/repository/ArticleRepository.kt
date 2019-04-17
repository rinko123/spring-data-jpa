package com.rinko.repository

import com.rinko.entity.manytoone.Article
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 *create by liuliang on 2019/4/17.
 */
interface ArticleRepository : JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
}