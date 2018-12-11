package com.rinko.entity.my

import com.lihongwei.entity.LongIdEntity
import org.hibernate.envers.Audited
import javax.persistence.Entity

/**
 *create by liuliang on 2018/12/7.
 */
@Entity
class Child : LongIdEntity() {

    var parentId:Long? = null

    var childName: String? = null
}