package com.odon.beprepared.service;

import com.odon.beprepared.model.City;
import com.odon.beprepared.model.Province;

import java.util.List;

public interface LocationService {
    List<Province> getAllProvinces();
    List<City>  guetAllCities();
    List<City> getAllCitiesProvincesId(long provinceId);
    Province getProvinceById(Long provinceId);
    City getCityById(Long cityId);

}
