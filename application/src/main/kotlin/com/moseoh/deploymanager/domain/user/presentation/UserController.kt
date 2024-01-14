package com.moseoh.deploymanager.domain.user.presentation

import com.moseoh.deploymanager.common.dto.ResponseDto
import com.moseoh.deploymanager.domain.user.application.UserService
import com.moseoh.deploymanager.domain.user.application.dto.CreateUserRequest
import com.moseoh.deploymanager.domain.user.application.dto.UpdateUserRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER','ADMIN')")
    fun create(
        @RequestBody @Valid userDto: CreateUserRequest
    ): ResponseEntity<ResponseDto> {
        userService.create(userDto)
        return ResponseDto.of(
            message = "완료.",
            status = HttpStatus.NO_CONTENT
        )
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER','ADMIN','USER')")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid userDto: UpdateUserRequest
    ): ResponseEntity<ResponseDto> {
        userService.update(id, userDto)
        return ResponseDto.of(
            message = "완료.",
            status = HttpStatus.NO_CONTENT
        )
    }
}