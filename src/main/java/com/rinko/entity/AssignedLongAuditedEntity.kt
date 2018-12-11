package com.lihongwei.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * Created by hongwei on 14/06/2017.
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener::class)
abstract class AssignedLongAuditedEntity : AssignedLongIdEntity() {

    @get:Column
    @get:CreatedBy
    @get:JsonIgnore
    //@get:Comment("创建人userId")
    open var createById: Long? = null

    @get:Column
    @get:LastModifiedBy
    @get:JsonIgnore
    //@get:Comment("最后修改人userId")
    open var updateById: Long? = null
}