package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Branch
import org.springframework.data.jpa.repository.JpaRepository

interface BranchRepository : JpaRepository<Branch, String>
