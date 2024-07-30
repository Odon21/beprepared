package com.odon.beprepared.mapper;

import com.odon.beprepared.dto.request.AlertRequestDto;
import com.odon.beprepared.dto.request.CitizenRequestDto;
import com.odon.beprepared.dto.request.UserRequestDto;
import com.odon.beprepared.dto.response.*;
import com.odon.beprepared.dto.response.CityResponseDto;
import com.odon.beprepared.model.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;
    public ProvinceResponseDto mapProvinceToResponceDto(Province province){
        return modelMapper.map(province, ProvinceResponseDto.class);

    }
    public List<ProvinceResponseDto> mapProvinceToResponseDtoList(List<Province> provinces){
        return provinces.stream().map(this::mapProvinceToResponceDto)
                .collect(Collectors.toList());
    }

    public CityResponseDto mapCityResponseDto(City city){
        return modelMapper.map(city, CityResponseDto.class);

    }
    public List<CityResponseDto> mapCityToResponseDtoList(List<City> cities){
        return cities.stream().map(this::mapCityResponseDto)
                .collect(Collectors.toList());
    }
    public Alert mapAlertRequestToModel(AlertRequestDto alertRequestDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(alertRequestDto, Alert.class);
    }

    public AlertResponseDto mapAlertToResponseDto(Alert alert){
        return AlertResponseDto.builder()
                .id(alert.getId())
                .title(alert.getTitle())
                .message(alert.getMessage())
                .severity(alert.getSeverity())
                .province(alert.getProvince().getDesignation())
                .city(alert.getCity().getDesignation())
                .isActive(alert.isActive())
                .build();
    }

    public List<AlertResponseDto> mapAlertToResponseDtoList(List<Alert> alerts){
        return alerts.stream().map(this::mapAlertToResponseDto)
                .collect(Collectors.toList());
    }
    public User mapUserRequestToModel(UserRequestDto userRequestDto){
        return modelMapper.map(userRequestDto, User.class);
    }

    public UserResponseDto mapUserToResponseDto(User user){
        return modelMapper.map(user, UserResponseDto.class);
    }


    public CitizenResponseDto mapCitizenToResponseDto(Citizen citizen) {
        return  CitizenResponseDto.builder()
                .id(citizen.getId())
                .phone(citizen.getPhone())
                .deviceId(citizen.getDeviceId())
                .verified(citizen.isVerified())
                .city(citizen.getCity().getDesignation())
                .province(citizen.getCity().getProvince().getDesignation())
                .build();
    }

    public List<CitizenResponseDto> mapCitizenToResponseDtoList(List<Citizen> citizens){
        return citizens.stream().map(this::mapCitizenToResponseDto)
                .collect(Collectors.toList());
    }

    public Citizen mapCitizenRequestToModel(CitizenRequestDto citizenRequestDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(citizenRequestDto, Citizen.class);
    }
}
