package com.github.aimanzaki.springbootdz.utils

import org.aspectj.lang.annotation.Pointcut

class CommonPointcut {
    @Pointcut("@annotation(com.github.aimanzaki.springbootdz.utils.annotations.TestAnnotate)")
    fun testCustomAnnotate() {}
}
