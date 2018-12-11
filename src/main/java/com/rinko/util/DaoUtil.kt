package com.rinko.util

import com.lihongwei.entity.SuperEntity
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * Created by hongwei on 20/10/2016.
 */
object DaoUtil {
    /**
     * 设置默认的排序参数
     */
    fun setOrder(pageable: Pageable, defaultSort: Sort, vararg fields: Pair<String, String>): Pageable {
        return if (pageable.sort == null || pageable.sort.isUnsorted) {
            PageRequest.of(pageable.pageNumber, pageable.pageSize, defaultSort)
        } else {
            var direction: Sort.Direction? = null
            var prop: String? = null
            fields.forEach {
                val oi = pageable.sort.getOrderFor(it.first)
                if (oi != null) {
                    direction = oi.direction
                    prop = it.second
                }
            }
            if (prop != null) {
                PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(direction!!, prop))
            } else {
                pageable
            }
        }
    }

    /**
     * 复制实体类，默认忽略id和一些与版本、审计相关的参数
     */
    fun copyEntity(newEntity: SuperEntity, oldEntity: SuperEntity, vararg others: String) {
        BeanUtils.copyProperties(newEntity, oldEntity, "id", "createTime", "updateTime", "version", "createBy", "updateBy", *others)
    }

    fun <T : SuperEntity> childSave(oldEntities: List<T>, newEntities: List<T>, vararg ignoreProperties: String): Pair<List<T>, List<T>> {
        val updateList = mutableListOf<T>()
        val deleteList = mutableListOf<T>()
        if (newEntities.size >= oldEntities.size) {
            updateList.addAll(oldEntities)
            if (newEntities.size > oldEntities.size) {
                updateList.addAll(newEntities.subList(oldEntities.size, newEntities.size))
            }
            for (i in (0 until oldEntities.size)) {
                copyEntity(newEntities.get(i), updateList.get(i), *ignoreProperties)
            }
        } else {
            deleteList.addAll(oldEntities.subList(newEntities.size, oldEntities.size))
            updateList.addAll(oldEntities.subList(0, newEntities.size))
            for (i in (0 until updateList.size)) {
                copyEntity(newEntities.get(i), updateList.get(i), *ignoreProperties)
            }
        }
        return Pair(updateList, deleteList)
    }

    fun <T> genChildEntities(oldEntities: List<T>, newEntities: List<T>?, createEntity: (T) -> T, vararg ignoreProperties: String): Pair<List<T>, List<T>> {
        val newEns = newEntities ?: listOf<T>()
        val saves = if (oldEntities.size >= newEns.size) oldEntities.subList(0, newEns.size) else {
            val ss = ArrayList<T>(newEns.size)
            ss.addAll(oldEntities)
            (oldEntities.size until newEns.size).mapTo(ss) { createEntity(newEns[it]) }
            ss
        }
        val deletes = if (oldEntities.size >= newEns.size) oldEntities.subList(newEns.size, oldEntities.size) else listOf<T>()
        for (i in 0 until saves.size) {
            copyEntity(newEns[i] as SuperEntity, saves[i] as SuperEntity, "parentId", *ignoreProperties)
        }
        return Pair(saves, deletes)
    }

    fun <T> genChildEntity(oldEntity: T?, newEntity: T?, createEntity: (T) -> T, vararg ignoreProperties: String): Pair<List<T>, List<T>> {
        val oldEntities = if (oldEntity == null) listOf<T>() else listOf(oldEntity)
        val newEntities = if (newEntity == null) listOf<T>() else listOf(newEntity)
        return genChildEntities(oldEntities, newEntities, createEntity, *ignoreProperties)
    }

    /**
     * 避免查询的时候查询到空页面
     * 如果查询的时候，查询到当前页为空，而实际上有数据的时候，就返回到有数据的最后一页数据
     */
    fun <T> omitEmptyPage(pageable: Pageable, func: (Pageable) -> Page<T>): Page<T> {
        val result = func(pageable)
        return if (result.numberOfElements == 0 && result.totalElements > 0) {
            func(PageRequest.of(result.totalPages - 1, pageable.pageSize, pageable.sort))
        } else {
            result
        }
    }
}
