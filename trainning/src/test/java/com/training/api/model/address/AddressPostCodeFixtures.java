package com.training.api.model.address;

import com.training.api.model.city.City;

import java.util.List;

public class AddressPostCodeFixtures {
    public static List<AddressPostCode> createAddressPostCode() {
        AddressPostCode addressPostCode = new AddressPostCode();

        addressPostCode.setCityCode("05207");
        addressPostCode.setCityName("湯沢市");
        addressPostCode.setCityKana("ﾕｻﾞﾜｼ");
        addressPostCode.setPrefectureName("秋田県");
        addressPostCode.setPrefectureKana("ｱｷﾀｹﾝ");
        addressPostCode.setPrefectureCode("05");
        addressPostCode.setAreaName("東松沢");
        addressPostCode.setAreaKana("ﾋｶﾞｼﾏﾂｻﾞﾜ");
        addressPostCode.setMultiPostArea(0);
        addressPostCode.setKoazaArea(0);
        addressPostCode.setChomeArea(0);
        addressPostCode.setOldPostCode("012");
        addressPostCode.setPostCode("0120861");
        addressPostCode.setMultiArea(0);
        addressPostCode.setUpdateShow(0);
        addressPostCode.setChangeReason(0);
        return (List<AddressPostCode>) addressPostCode;
    }
}
