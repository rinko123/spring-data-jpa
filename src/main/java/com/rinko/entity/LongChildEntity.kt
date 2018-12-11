package com.lihongwei.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * Created by hongwei on 29/06/2017.
 */
@MappedSuperclass
@Audited
abstract class LongChildEntity : LongIdEntity() {
    @get:Column
    @get:JsonIgnore
    open var parentId: Long? = null
}