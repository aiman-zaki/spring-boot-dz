package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.dto.BranchDto
import com.github.aimanzaki.springbootdz.dto.PageDto
import com.github.aimanzaki.springbootdz.services.BranchService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/branches")
class BranchesController(
    private val branchService: BranchService,
) {

    companion object {
        private const val DEFAULT_SORT_BY = "createdAt"
        private const val DEFAULT_SORT_DIRECTION = "DESC"
    }

    @GetMapping
    fun getBranches(
        @RequestParam(name = "id", defaultValue = "") branchId: String?,
        @RequestParam(name = "name", defaultValue = "") name: String?,
        @RequestParam(name = "code", defaultValue = "") code: String?,
        @RequestParam(name = "isActive") isActive: Boolean,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_BY) sortBy: String,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_DIRECTION) sortDirection: String,
        @RequestParam(required = true) pageNumber: Int,
        @RequestParam(required = true) pageSize: Int,
    ): ResponseEntity<PageDto<BranchDto>> {
        return ResponseEntity.ok(
            this.branchService.getBranchWithFilter(
                id = branchId,
                name = name,
                code = code,
                isActive = isActive,
                pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection), sortBy),
            )
        )
    }

    @PostMapping
    fun createBranch(@RequestBody branchDto: BranchDto) {
        this.branchService.createBranch(branchDto)
    }

    @PutMapping("/{branchId}")
    fun updateBranch(@PathVariable(name = "branchId") branchId: UUID, @RequestBody branchDto: BranchDto) {
        ResponseEntity.ok(this.branchService.updateBranch(branchId, branchDto))
    }
}
