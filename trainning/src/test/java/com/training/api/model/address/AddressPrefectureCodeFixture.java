package com.training.api.model.address;

import java.util.List;

public class AddressPrefectureCodeFixture {
    public static List<AddressPrefectureCode> createAddressPrefectureCode() {
        AddressPrefectureCode addressPrefectureCode = new AddressPrefectureCode();
        addressPrefectureCode.setCityCode("10201");
        addressPrefectureCode.setCityName("前橋市");
        addressPrefectureCode.setCityKana("ﾏｴﾊﾞｼｼ");
        addressPrefectureCode.setPrefectureName("群馬県");
        addressPrefectureCode.setPrefectureKana("ｸﾞﾝﾏｹﾝ");
        addressPrefectureCode.setPrefectureCode("10");
        return (List<AddressPrefectureCode>) addressPrefectureCode;
    }
}
