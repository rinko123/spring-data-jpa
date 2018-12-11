package com.lihongwei.entity

import org.hibernate.envers.Audited
import java.io.Serializable
import javax.persistence.MappedSuperclass

/**
 * Created by hongwei on 14/06/2017.
 */
@MappedSuperclass
@Audited
abstract class SuperEntity : Serializable