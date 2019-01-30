package com.rinko.entity.my

import com.rinko.repository.ParentRepository
import com.rinko.service.ParentService
import com.rinko.util.DaoUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner

/**
 * create by liuliang on 2019/1/30.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ParentTest{

    @Autowired
    private lateinit var parentRepository: ParentRepository

    @Test
    fun findByChildName(){
        val pageable = PageRequest.of(0,20)
        val p =DaoUtil.setOrder(pageable,Parent.DEFAULT_ORDER)
        val omitEmptyPage = DaoUtil.omitEmptyPage(p) {
            parentRepository.findAll(Parent.specFindAll(Child().apply {
                this.childName = "erzi1"
            }), it)
        }
        println(omitEmptyPage)
    }

}