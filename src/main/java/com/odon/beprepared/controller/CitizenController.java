package com.odon.beprepared.controller;

import com.odon.beprepared.dto.request.CitizenRequestDto;
import com.odon.beprepared.dto.response.CitizenResponseDto;
import com.odon.beprepared.mapper.Mapper;
import com.odon.beprepared.service.CitizenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/citizens")
public class CitizenController {
    private final Mapper mapper;
    private final CitizenService citizenService;

    @PostMapping("/")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenRequestDto citizenRequestDto){
        return new ResponseEntity<>(citizenService.createCitizen(
                mapper.mapCitizenRequestToModel(citizenRequestDto),
                citizenRequestDto.getCityId()),
                HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizens(){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizens()
        ));
    }
    @GetMapping("/province")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitiezensByProvince(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensByProvinceId(id)
        ));
    }@GetMapping("/city")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizensByCity(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensCityId(id)
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponseDto> getCitizenById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDto(
                citizenService.getCitizenById(id)
        ));
    }
    @PutMapping("/cerify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String opt){
        return ResponseEntity.ok(citizenService.verifyAcount(opt));
    }
}
