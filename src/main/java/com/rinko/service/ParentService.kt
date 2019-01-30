package com.rinko.service

import com.rinko.entity.my.Parent
import com.rinko.repository.ParentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *create by liuliang on 2019/1/30.
 */
@Service
@Transactional
class ParentService(override val repository: ParentRepository) : SuperService<Parent, Long> {


}