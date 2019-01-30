package com.rinko.service

import com.lihongwei.entity.SuperEntity
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.io.Serializable
import java.util.*
import javax.persistence.EntityManager

interface SuperService<T : SuperEntity, ID : Serializable> {
    val repository: JpaRepository<T, ID>

    /**
     * 保存单个实体
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    fun <S : T> save(entity: S): S = repository.save(entity)

    /**
     * 保存多个实体
     *
     * @param entities must not be null.
     * @return the saved entities will never be null.
     * @throws IllegalArgumentException in case the given entity is null.
     */
    fun <S : T> saveAll(entities: Iterable<S>): List<S> = repository.saveAll(entities)

    /**
     * Flushes all pending changes to the database.
     */
    fun flush() {
        repository.flush()
    }

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity
     * @return the saved entity
     */
    fun <S : T> saveAndFlush(entity: S): S = repository.saveAndFlush(entity)

    /**
     * 按id删除实体
     *
     * @param id must not be null.
     * @throws IllegalArgumentException in case the given `id` is null
     */
    fun deleteById(id: ID) {
        repository.deleteById(id)
    }

    /**
     * 删除实体
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is null.
     */
    fun delete(entity: T) {
        repository.delete(entity)
    }

    /**
     * 删除多个实体
     *
     * @param entities
     * @throws IllegalArgumentException in case the given [Iterable] is null.
     */
    fun deleteAll(entities: Iterable<T>) {
        repository.deleteAll(entities)
    }

    /**
     * 删除所有实体
     */
    fun deleteAll() {
        repository.deleteAll()
        repository.deleteAllInBatch()
    }

    /**
     * Deletes the given entities in a batch which means it will create a single [Query]. Assume that we will clear
     * the [javax.persistence.EntityManager] after the call.
     *
     * @param entities
     */
    fun deleteInBatch(entities: Iterable<T>) {
        repository.deleteInBatch(entities)
    }

    /**
     * Deletes all entities in a batch call.
     */
    fun deleteAllInBatch() {
        repository.deleteAllInBatch()
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be null.
     * @return the entity with the given id or Optional#empty() if none found
     * @throws IllegalArgumentException if `id` is null.
     */
    fun findById(id: ID): Optional<T> = repository.findById(id)

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be null.
     * @return true if an entity with the given id exists, false otherwise.
     * @throws IllegalArgumentException if `id` is null.
     */
    fun existsById(id: ID): Boolean = repository.existsById(id)

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun findAll(): List<T> = repository.findAll()

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    fun findAllById(ids: Iterable<ID>): List<T> = repository.findAllById(ids)

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    fun count(): Long = repository.count()

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort
     * @return all entities sorted by the given options
     */
    fun findAll(sort: Sort): List<T> = repository.findAll(sort)

    /**
     * Returns a [Page] of entities meeting the paging restriction provided in the `Pageable` object.
     *
     * @param pageable
     * @return a page of entities
     */
    fun findAll(pageable: Pageable): Page<T> = repository.findAll(pageable)

    /**
     * Returns a reference to the entity with the given identifier.
     *
     * @param id must not be null.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager.getReference
     * @throws javax.persistence.EntityNotFoundException if no entity exists for given `id`.
     */
    fun getOne(id: ID): T = repository.getOne(id)

    /**
     * Returns all entities matching the given [Example]. In case no match could be found an empty [Iterable]
     * is returned.
     *
     * @param example must not be null.
     * @return all entities matching the given [Example].
     */
    fun <S : T> findAll(example: Example<S>): List<S> = repository.findAll(example)

    /**
     * Returns all entities matching the given [Example] applying the given [Sort]. In case no match could be
     * found an empty [Iterable] is returned.
     *
     * @param example must not be null.
     * @param sort the [Sort] specification to sort the results by, must not be null.
     * @return all entities matching the given [Example].
     * @since 1.10
     */
    fun <S : T> findAll(example: Example<S>, sort: Sort): List<S> = repository.findAll(example, sort)

    /**
     * Returns a single entity matching the given [Example] or null if none was found.
     *
     * @param example must not be null.
     * @return a single entity matching the given [Example] or [Optional.empty] if none was found.
     * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    fun <S : T> findOne(example: Example<S>): Optional<S> = repository.findOne(example)


    /**
     * Returns a [Page] of entities matching the given [Example]. In case no match could be found, an empty
     * [Page] is returned.
     *
     * @param example must not be null.
     * @param pageable can be null.
     * @return a [Page] of entities matching the given [Example].
     */
    fun <S : T> findAll(example: Example<S>, pageable: Pageable): Page<S> = repository.findAll(example, pageable)

    /**
     * Returns the number of instances matching the given [Example].
     *
     * @param example the [Example] to count instances for. Must not be null.
     * @return the number of instances matching the [Example].
     */
    fun <S : T> count(example: Example<S>): Long = repository.count(example)

    /**
     * Checks whether the data store contains elements that match the given [Example].
     *
     * @param example the [Example] to use for the existence check. Must not be null.
     * @return true if the data store contains elements that match the given [Example].
     */
    fun <S : T> exists(example: Example<S>): Boolean = repository.exists(example)
}