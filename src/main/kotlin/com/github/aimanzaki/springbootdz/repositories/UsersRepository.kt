package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
interface UsersRepository : JpaRepository<User, UUID>
