package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SuppliersRepository : JpaRepository<Supplier, UUID>
