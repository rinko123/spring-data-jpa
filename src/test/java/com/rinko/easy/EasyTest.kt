package com.rinko.easy

import com.rinko.entity.my.Child
import com.rinko.repository.ChildRepository
import com.rinko.entity.my.Parent
import com.rinko.repository.ParentRepository
import com.rinko.util.DaoUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * create by liuliang on 2018/10/12.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class EasyTest {

    @Autowired
    private lateinit var childRepository: ChildRepository

    @Autowired
    private lateinit var parentRepository: ParentRepository

    @Test
    fun initData2() {
        val child = Child().apply {
            this.childName = "erzi1"
        }//6476695088230699048
        childRepository.save(child)
        val parent = Parent().apply {
            this.parentName = "fu1"
        }//6476695088633353284
        parentRepository.save(parent)

    }

    @Test
    fun  persistence() {
        val parent = parentRepository.findById(6476695088633353284).get()
        val childs = childRepository.findByParentId(6476695088633353284)
        parent.parentName = "123"
        childs.forEach {
            it.childName = "234"
        }
    }

    @Test
    fun  testDaoUtil() {
        val parent = parentRepository.findById(6476695088633353284).get()
        val childs = childRepository.findByParentId(6476695088633353284)
        val old = listOf<Child>()
//        DaoUtil.genChildEntities(old,childs,()->Child(),)
    }




}