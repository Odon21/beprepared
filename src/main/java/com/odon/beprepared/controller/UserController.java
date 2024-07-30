package com.odon.beprepared.controller;

import com.odon.beprepared.dto.request.UserRequestDto;
import com.odon.beprepared.dto.response.StatsResponse;
import com.odon.beprepared.dto.response.UserResponseDto;
import com.odon.beprepared.mapper.Mapper;
import com.odon.beprepared.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final Mapper mapper;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.createUser(
                mapper.mapUserRequestToModel(userRequestDto)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.mapUserToResponseDto(
                userService.getUserById(id)
        ));
    }

    @GetMapping("/metrics")
    public ResponseEntity<StatsResponse> getMetrics(){
        return ResponseEntity.ok(userService.getAllStats());
    }
}
