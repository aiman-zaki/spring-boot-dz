package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.dto.BranchDto
import com.github.aimanzaki.springbootdz.services.BranchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/branches")
class BranchesController(
    private val branchService: BranchService,
) {

    @PostMapping
    fun createBranch(@RequestBody branchDto: BranchDto) {
        this.branchService.createBranch(branchDto)
    }

    @PutMapping("/{branchId}")
    fun updateBranch(@PathVariable(name = "branchId") branchId: UUID, @RequestBody branchDto: BranchDto) {
        ResponseEntity.ok(this.branchService.updateBranch(branchId, branchDto))
    }
}
