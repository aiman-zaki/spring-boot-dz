package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.repositories.StockWithDetailsRepository
import org.springframework.stereotype.Service

@Service
class StocksWithDetailsService(var stockWithDetailsRepository: StockWithDetailsRepository)
