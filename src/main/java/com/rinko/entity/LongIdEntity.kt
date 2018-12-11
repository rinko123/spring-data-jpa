package com.lihongwei.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

/**
 * Created by hongwei on 2017/3/13.
 */
@MappedSuperclass
@Audited
abstract class LongIdEntity : SuperEntity() {
    @get:Id
    @get:GeneratedValue(generator = "uniqueId")
    @get:GenericGenerator(name = "uniqueId", strategy = "com.rinko.config.hibernate.UniqueIdGenerator")
    @get:JsonSerialize(using = ToStringSerializer::class)
    //@get:Comment("编号")
    open var id: Long? = null

    @get:Temporal(value = TemporalType.TIMESTAMP)
    @get:CreationTimestamp
    @get:JsonIgnore
    //@get:Comment("创建时间")
    open var createTime: Date? = null

    @get:Temporal(value = TemporalType.TIMESTAMP)
    @get:UpdateTimestamp
    @get:JsonIgnore
    //@get:Comment("最后更新时间")
    open var updateTime: Date? = null

    @get:Version
    @get:JsonIgnore
    //@get:Comment("乐观锁版本号")
    open var version: Long? = null
}