package com.rinko.config.hibernate

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable

/**
 * Created by hongwei on 7/17/16.
 */
class UniqueIdGenerator : IdentifierGenerator {
    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Serializable {
        return UniqueId.genId()
    }
}
