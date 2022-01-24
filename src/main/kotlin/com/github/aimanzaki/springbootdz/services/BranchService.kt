package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.datamapping.toEntity
import com.github.aimanzaki.springbootdz.dto.BranchDto
import com.github.aimanzaki.springbootdz.models.Branch
import com.github.aimanzaki.springbootdz.repositories.BranchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BranchService(private val branchRepository: BranchRepository) {

    fun createBranch(branchDto: BranchDto) {
        branchRepository.save(branchDto.toEntity(Branch()))
    }

    fun updateBranch(id: UUID, branchDto: BranchDto) {
        val branch = branchRepository.findByIdOrNull(id)
        if (branch != null) {
            branchRepository.save(branchDto.toEntity(branch))
        }
    }
}
