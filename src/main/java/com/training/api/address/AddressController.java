package com.training.api.address;

import com.training.api.model.address.AddressPostCode;
import com.training.api.model.address.AddressPrefectureCode;
import com.training.api.model.address.AddressService;
import com.training.api.model.address.ResourceAddressPostCode;
import com.training.api.model.address.ResourceAddressPrefectureCode;
import jp.xet.sparwings.spring.web.httpexceptions.HttpNotFoundException;
import jp.xet.sparwings.spring.web.httpexceptions.HttpBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to search {@link AddressPrefectureCode} and {@link AddressPostCode}.
 */
@RestController
@RequestMapping("post_offices")
public class AddressController {

    @Autowired
    AddressService areaService;
    /**
     * Find list address by post code
     * @param postCode code of post at address
     * @return found of list address (to area)
     */
    @RequestMapping(value = "/post/{postcode}", method = RequestMethod.GET)
    public ResponseEntity SearchByPostCode(@PathVariable("postcode") String postCode) {
        try {
            List<AddressPostCode> addressPostCodes = areaService.findByPostCode(postCode);
            ResourceAddressPostCode resourceAddressPostCode = new ResourceAddressPostCode(addressPostCodes);
            return ResponseEntity.ok(resourceAddressPostCode);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new HttpBadRequestException(e.getMessage());
        } catch (HttpNotFoundException e) {
            throw new HttpNotFoundException(e.getMessage());
        }
    }
    @Autowired
    AddressService cityService;
    /**
     * Find list address by post code
     * @param prefectureCode code of post at address
     * @return found of list address (to area)
     */
    @RequestMapping(value = "/prefectures/{prefectureCode}", method = RequestMethod.GET)
    public ResponseEntity SearchByPrefectureCode(@PathVariable("prefectureCode") String prefectureCode) {
        try {
            List<AddressPrefectureCode> addressPrefectureCode = cityService.findByPrefectureCode(prefectureCode);
            ResourceAddressPrefectureCode resourceAddressPrefectureCode = new ResourceAddressPrefectureCode(addressPrefectureCode);
            return ResponseEntity.ok(resourceAddressPrefectureCode);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new HttpBadRequestException(e.getMessage());
        } catch (HttpNotFoundException e) {
            throw new HttpNotFoundException(e.getMessage());
        }
    }
}
