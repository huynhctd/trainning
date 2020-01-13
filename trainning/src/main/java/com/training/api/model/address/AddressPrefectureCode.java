package com.training.api.model.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.training.api.model.city.City;
import com.training.api.model.prefecture.Prefecture;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address prefecture code model
 */
@Data
@NoArgsConstructor
public class AddressPrefectureCode {
    /**
     * City code.
     */
    @JsonProperty("code")
    private String cityCode;

    /**
     * City name.
     */
    @JsonProperty("city")
    private String cityName;

    /**
     * City name kana.
     */
    @JsonProperty("city_kana")
    private String cityKana;

    /**
     * Prefecture name.
     */
    @JsonProperty("prefecture")
    private String prefectureName;

    /**
     * Prefecture name kana.
     */
    @JsonProperty("prefecture_kana")
    private String prefectureKana;

    /**
     * Prefecture code.
     */
    @JsonProperty("prefecture_code")
    private String prefectureCode;

    /**
     * Creates {@link AddressPrefectureCode} instance content collection.
     * @param city found of list city
     */
    public AddressPrefectureCode(City city) {
        Preconditions.checkNotNull(city,"PrefectureCode not found");
        Prefecture prefecture = city.getPrefecture();
        Preconditions.checkNotNull(prefecture,"PrefectureCode not found");
        this.cityCode = city.getCityCode();
        this.cityName = city.getCityName();
        this.cityKana = city.getCityKana();
        this.prefectureName = prefecture.getPrefectureName();
        this.prefectureKana = prefecture.getPrefectureKana();
        this.prefectureCode = prefecture.getPrefectureCode();
    }
}