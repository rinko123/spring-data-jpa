package com.lihongwei.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * Created by hongwei on 13/07/2017.
 */
@MappedSuperclass
@Audited
abstract class StringChildEntity : StringIdEntity() {
    @get:Column(length = 127)
    @get:JsonIgnore
    open var parentId: String? = null
}