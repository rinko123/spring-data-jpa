package com.rinko.entity.my

import com.lihongwei.entity.LongIdEntity
import jdk.nashorn.internal.runtime.Specialization
import org.hibernate.envers.Audited
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import javax.persistence.Entity
import javax.persistence.criteria.*

/**
 *create by liuliang on 2018/12/7.
 */
@Entity
@Audited
class Parent : LongIdEntity() {

    var parentName: String? = null

    companion object {

        val DEFAULT_ORDER = Sort(Sort.Direction.DESC, "id")

        val specFindAll = { child: Child ->
            Specification<Parent> { root: Root<Parent>, criteriaQuery: CriteriaQuery<*>, cb: CriteriaBuilder ->
                val predicates = mutableListOf<Predicate>()
                if (child.childName != null) {
                    val join = root.join<Parent, Child>("parentId")

                    predicates.add(cb.equal(root.get<Long>("id"), join.get<Long>("parentId")))
                    predicates.add(cb.equal( join.get<Long>("parentId"),child.childName!!))

                }
                 cb.and(*predicates.toTypedArray())
            }
        }
    }
}