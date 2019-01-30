package com.rinko.service

import com.rinko.entity.my.Child
import com.rinko.repository.ChildRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *create by liuliang on 2019/1/30.
 */
@Service
@Transactional
class ChildService(override val repository: ChildRepository) : SuperService<Child, Long> {


}