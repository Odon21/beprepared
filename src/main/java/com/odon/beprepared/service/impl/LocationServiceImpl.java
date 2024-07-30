package com.odon.beprepared.service.impl;

import com.odon.beprepared.model.City;
import com.odon.beprepared.model.Province;
import com.odon.beprepared.repository.CityRepository;
import com.odon.beprepared.repository.ProvinceRepository;
import com.odon.beprepared.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> guetAllCities() {
        return cityRepository.findAll();

    }
    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCitiesProvincesId(long provinceId) {

        return cityRepository.findAllByProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Province getProvinceById(Long provinceId) {

        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("A provincia nao foi encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getCityById(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(() ->
                new EntityNotFoundException("O Distrito nao foi encontrado!"));
    }
}
