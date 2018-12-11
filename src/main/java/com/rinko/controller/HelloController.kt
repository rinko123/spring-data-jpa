package com.rinko.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 *create by liuliang on 2018/10/17.
 */
@RestController
class HelloController {

//    @Autowired
//    private lateinit var userService: UserService

    @RequestMapping("login")
    fun sayHello(num: Int, loginName: String,httpServletRequest: HttpServletRequest): String {
//        userService.decideUser(num, loginName)
        return "111"
    }

    @RequestMapping("hello2")
    fun sayHello2(@RequestParam(defaultValue = "abc")none:String?): Map<String,Array<Int>> {
        val mutableMapOf = mutableMapOf(Pair("a", arrayOf(1, 2, 3)), Pair("b", arrayOf(5, 8)))
        println(none)
        return mutableMapOf
    }

}