package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.DashboardId
import com.github.aimanzaki.springbootdz.models.OkbDashboard
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface OkbDashboardRepository : CrudRepository<OkbDashboard, DashboardId> {

    @Query(
        "SELECT " +
            "new com.github.aimanzaki.springbootdz.api.response.OkbDashboardDto(okbd.monthYear,okbd.productType,okbd.userName,SUM(okbd.numberOfClicks))" +
            "FROM OkbDashboard okbd " +
            "WHERE okbd.userName = ?1 AND okbd.monthYear = ?2 and okbd.productType = ?3" +
            "GROUP BY okbd.monthYear "
    )
    fun findByUsernameAndYearAndProductTypeAndSumByYear(userName: String, monthYear: String, productType: String)
}
