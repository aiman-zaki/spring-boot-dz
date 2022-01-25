package com.github.aimanzaki.springbootdz.datamapping

import com.github.aimanzaki.springbootdz.dto.BranchDto
import com.github.aimanzaki.springbootdz.models.Branch

fun BranchDto.toEntity(branch: Branch): Branch {
    branch.code = this.code
    branch.isActive = this.isActive
    branch.name = this.name
    return branch
}

fun Branch.toDto() = BranchDto(
    id = this.id,
    name = this.name,
    code = this.code,
    isActive = this.isActive,

)
