package com.rinko.config.hibernate

import com.rinko.config.hibernate.UniqueId
import org.springframework.beans.factory.annotation.Value

import java.security.SecureRandom

/**
 * id 生成器
 *
 *
 * Created by hongwei on 7/17/16.
 */
//@Component
//@Lazy(false)
class UniqueId {

    @Value("\${worker:1}")
    fun setWorker(worker: Long?) {
        UniqueId.worker = worker!! shl 12
    }

    companion object {
        @Volatile private var seq: Long = 0
        private const val datacenter = (1 shl 17).toLong()
        private var worker = (1 shl 12).toLong()
        private val random = SecureRandom()

        /**
         * 生成ID
         * ID生成规则：
         * 42 bits: Timestamp (毫秒级)
         * 9 bits: 节点 ID (datacenter ID 4 bits + worker ID 5 bits)
         * 12 bits: sequence number

         * @return
         */
        fun genId(): Long {
            val time = System.currentTimeMillis() shl 22
            val r = random.nextInt().toLong() and 0xe1fL
            val s = ++seq and 0xfL shl 5
            return time or datacenter or worker or r or s
        }
    }
}
