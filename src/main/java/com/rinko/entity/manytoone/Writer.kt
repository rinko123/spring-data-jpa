package com.rinko.entity.manytoone

import com.lihongwei.entity.LongIdEntity
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
class Writer : LongIdEntity() {

    var writerName: String? = null

    companion object {

        val DEFAULT_ORDER = Sort(Sort.Direction.DESC, "id")

    }
}