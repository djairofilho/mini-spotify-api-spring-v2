package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.DtoMapper;
import com.example.miniSpotify.dto.UserRequest;
import com.example.miniSpotify.dto.UserResponse;
import com.example.miniSpotify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return DtoMapper.toResponse(userService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(userService.findById(id));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return DtoMapper.toResponse(userService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
