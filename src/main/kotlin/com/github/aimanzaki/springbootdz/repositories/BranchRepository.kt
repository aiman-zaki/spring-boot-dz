package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Branch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface BranchRepository : JpaRepository<Branch, UUID> {

    @Query(
        "SELECT b FROM Branch b " +
            "WHERE CAST(id as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:id,'%') AND " +
            "name LIKE CONCAT('%',:name,'%') AND " +
            "code LIKE CONCAT('%',:code,'%') AND " +
            "isActive = :isActive"
    )
    fun findWithFilterAndPageable(
        @Param("id") id: String?,
        @Param("name") name: String?,
        @Param("code") code: String?,
        @Param("isActive") isActive: Boolean,
        pageable: Pageable,
    ): Page<Branch>
}
