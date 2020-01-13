package com.training.api.model.address;

import com.training.api.common.Validate;
import com.training.api.model.area.Area;
import com.training.api.model.area.AreaRepository;
import com.training.api.model.city.City;
import com.training.api.model.city.CityRepository;
import jp.xet.sparwings.spring.web.httpexceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Address service
 */
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AreaRepository areaRepository;

    private final CityRepository cityRepository;

    /**
     * Find list area by post code
     * @param postCode code of post at address
     * @return found of list address (to area)
     * @throws HttpNotFoundException if areas = null
     */
    public List<AddressPostCode> findByPostCode(String postCode) {
        Preconditions.checkNotNull(postCode,"Code must be not null");
        postCode = Validate.AddressValidates.replaceCode(postCode);
        Validate.AddressValidates.checkHalfSize(postCode);
        List<Area> areas = areaRepository.findByPostPostCode(postCode);
        if(areas.isEmpty()){
            throw new HttpNotFoundException("PostCode not found");
        }
        return areas.stream().map(AddressPostCode::new).collect(Collectors.toList());
    }

    /**
     * Find list area by post code
     * @param prefectureCode code of post at address
     * @return found of list address (to area)
     * @throws HttpNotFoundException if areas = null
     */
    public List<AddressPrefectureCode> findByPrefectureCode(String prefectureCode)  {
        Preconditions.checkNotNull(prefectureCode,"Code must be not null");
        prefectureCode = Validate.AddressValidates.replaceCode(prefectureCode);
        Validate.AddressValidates.checkHalfSize(prefectureCode);
        List<City> cities = cityRepository.findByPrefecturePrefectureCode(prefectureCode);
        if(cities.isEmpty()){
            throw new HttpNotFoundException("PrefectureCode not found");
        }
        return cities.stream().map(AddressPrefectureCode::new).collect(Collectors.toList());
    }
}