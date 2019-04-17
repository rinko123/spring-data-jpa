package com.rinko.entity.manytoone

import com.lihongwei.entity.LongIdEntity
import com.rinko.entity.my.Child
import com.rinko.entity.my.Parent
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.Specification
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.criteria.*

/**
 *create by liuliang on 2018/12/7.
 */
@Entity
@Audited
class Article : LongIdEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerId")
    var writer: Writer? = null

    var articleName: String? = null

    companion object {

        val specFindAll = { article: Article ->
            Specification<Article> { root: Root<Article>, criteriaQuery: CriteriaQuery<*>, cb: CriteriaBuilder ->
                val predicates = mutableListOf<Predicate>()
                if (article.articleName != null) {
                    val join = root.join<Writer, Article>("writer")
//                    val userJoin = root.join(root.model.getSingularAttribute("writer", Writer::class.java), JoinType.LEFT)
                    predicates.add(cb.like(join.get<String>("writerName"), "%${article.articleName}%"))
                }
                cb.and(*predicates.toTypedArray())
            }
        }
    }
}