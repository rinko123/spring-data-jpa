package com.rinko.entity.manytoone

import com.rinko.entity.my.Child
import com.rinko.entity.my.Parent
import com.rinko.repository.ArticleRepository
import com.rinko.repository.WriterRepository
import com.rinko.util.DaoUtil
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner

/**
 * create by liuliang on 2019/4/17.
 */
@SpringBootTest
@RunWith(SpringRunner::class)
class UserArticleControllerTest{

    @Autowired
    private lateinit var writerRepository: WriterRepository

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun findByChildName(){
        val pageable = PageRequest.of(0,20)
        val p = DaoUtil.setOrder(pageable, Parent.DEFAULT_ORDER)
        val omitEmptyPage = DaoUtil.omitEmptyPage(p) {
            articleRepository.findAll(Article.specFindAll(Article().apply {
                this.articleName = "erzi1"
            }), it)
        }
        println(omitEmptyPage)
    }

}