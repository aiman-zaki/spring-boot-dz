package com.github.aimanzaki.springbootdz.configs

import org.springframework.cloud.sleuth.Tracer
import org.springframework.context.annotation.Configuration
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

@Configuration(proxyBeanMethods = false)
class TracerFilterConfig(val tracer: Tracer) : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val currentSpan = tracer.currentSpan()
        if (currentSpan != null) {
            val resp = response as HttpServletResponse
            resp.addHeader("trace-id", currentSpan.context().traceId())
        }
        chain.doFilter(request, response)
    }
}
