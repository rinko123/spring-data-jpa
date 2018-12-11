package com.lihongwei.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

/**
 * Created by hongwei on 14/06/2017.
 */
@MappedSuperclass
@Audited
abstract class AssignedLongIdEntity : SuperEntity() {

    @get:Id
    @get:JsonSerialize(using = ToStringSerializer::class)
    open var id: Long? = null

    @get:Temporal(value = TemporalType.TIMESTAMP)
    @get:CreationTimestamp
    @get:JsonIgnore
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    open var createTime: Date? = null

    @get:Temporal(value = TemporalType.TIMESTAMP)
    @get:UpdateTimestamp
    @get:JsonIgnore
    @get:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    open var updateTime: Date? = null

    @get:Version
    @get:JsonIgnore
    @get:JsonSerialize(using = ToStringSerializer::class)
    open var version: Long? = null
}