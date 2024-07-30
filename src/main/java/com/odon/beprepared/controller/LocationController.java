package com.odon.beprepared.controller;

import com.odon.beprepared.dto.response.CityResponseDto;
import com.odon.beprepared.dto.response.ProvinceResponseDto;
import com.odon.beprepared.mapper.Mapper;
import com.odon.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
public class LocationController {
    private final Mapper mapper;
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDto>> getAllProvinces(){

        return ResponseEntity.ok(mapper.mapProvinceToResponseDtoList(
           locationService.getAllProvinces()
        ));
    }
    @GetMapping("/province")
    public ResponseEntity<ProvinceResponseDto> getProvinceByID(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapProvinceToResponceDto(
                locationService.getProvinceById(id)
        ));
    }
    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDto>> getAllCities(){
        return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
                locationService.guetAllCities()
        ));
    }

    @GetMapping("/cities/{provinceId}")
    public ResponseEntity<List<CityResponseDto>> getCityByProvinceId(@PathVariable Long provinceId){
        return ResponseEntity.ok(mapper.mapCityToResponseDtoList(
                locationService.getAllCitiesProvincesId(provinceId)
        ));
    }
    @GetMapping("/city")
    public ResponseEntity<CityResponseDto> getCityById(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCityResponseDto(
                locationService.getCityById(id)
        ));
    }

}
