package com.odon.beprepared.service;

import com.odon.beprepared.model.Citizen;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CitizenService {
    String createCitizen(Citizen citizen, Long cityId);

    List<Citizen> getAllCitizens();

    List<Citizen> getAllCitizensCityId(Long cityId);

    List<Citizen> getAllCitizensByProvinceId(Long provinceId);

    Citizen getCitizenById(Long id);

    String verifyAcount(String opt);

}
