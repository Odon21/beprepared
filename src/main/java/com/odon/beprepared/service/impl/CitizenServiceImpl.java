package com.odon.beprepared.service.impl;

import com.odon.beprepared.exception.BadRequestException;
import com.odon.beprepared.exception.EntityNotFoundException;
import com.odon.beprepared.model.Citizen;
import com.odon.beprepared.model.City;
import com.odon.beprepared.repository.CitizenRepository;
import com.odon.beprepared.service.CitizenService;
import com.odon.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final LocationService locationService;
    private  final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createCitizen(Citizen citizen, Long cityId) {
        if (citizenRepository.existsByPhone(citizen.getPhone())){
            throw new BadRequestException("Ja a um cidadao com esse numero!");
        }
        City city = locationService.getCityById(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setOtp(generateOpt(6));
        var savedCitizen = citizenRepository.save(citizen);
        return "Cidadao criado com sucesso! o seu OPT e: "+savedCitizen.getOtp();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensCityId(Long cityId) {
        return citizenRepository.findAllByCityId(cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {
        return citizenRepository.findAllByCityProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cidadao nao encontrado!"));
    }

    @Override
    @Transactional
    public String verifyAcount(String opt) {
        Citizen citizen =citizenRepository.findByOtp(opt).orElseThrow(()->
                new EntityNotFoundException("Cidadao nao encontrado!"));
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "A tua conta foi cerificada com sucesso!";
    }

    private static String generateOpt(int length){
        String otp = "";
        int x;
        char[] chars = new char[length];
        for (int i=0;i<length;i++){
            Random random = new Random();
            x = random.nextInt(9);
            chars[i] = Integer.toString(x).toCharArray()[0];
        }
        otp = new String(chars);
        return otp.trim();
    }
}
