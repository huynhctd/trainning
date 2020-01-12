package com.training.api.model.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

public class ResourceAddressPrefectureCode {
    @Getter
    @Setter
    @JsonProperty("data")
    private Collection<AddressPrefectureCode> data;

    /**
     * Creates a {@link ResourceAddressPostCode} instance content collection.
     ResourceAddressPrefectureCode
     * @param data list of {@link AddressPrefectureCode}
     */
    public ResourceAddressPrefectureCode(Collection<AddressPrefectureCode> data) {
        this.data = data;
    }
}
