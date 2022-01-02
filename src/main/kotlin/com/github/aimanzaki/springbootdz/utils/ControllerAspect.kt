package com.github.aimanzaki.springbootdz.utils

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Aspect
@Component
class ControllerAspect {

    @Throws(Throwable::class)
    @Around("com.github.aimanzaki.springbootdz.utils.CommonPointcut.testCustomAnnotate()")
    fun around(joinPoint: JoinPoint) {
        println("YO")
    }
}
