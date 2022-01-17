package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.StockHistory
import org.springframework.data.jpa.repository.JpaRepository

interface StockHistoryRepository : JpaRepository<StockHistory, Long>
