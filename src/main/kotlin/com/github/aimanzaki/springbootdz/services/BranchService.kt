package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.datamapping.toDto
import com.github.aimanzaki.springbootdz.datamapping.toEntity
import com.github.aimanzaki.springbootdz.datamapping.toPageDto
import com.github.aimanzaki.springbootdz.dto.BranchDto
import com.github.aimanzaki.springbootdz.dto.PageDto
import com.github.aimanzaki.springbootdz.models.Branch
import com.github.aimanzaki.springbootdz.repositories.BranchRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BranchService(private val branchRepository: BranchRepository) {

    fun getBranchWithFilter(
        id: String?,
        code: String?,
        name: String?,
        isActive: Boolean,
        pageable: Pageable,
    ): PageDto<BranchDto> {
        val data = branchRepository.findWithFilterAndPageable(id, code, name, isActive, pageable)
        val content = data.content.map { branch -> branch.toDto() }
        return data.toPageDto(content = content)
    }

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
