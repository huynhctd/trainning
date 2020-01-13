package com.training.api.model.address;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AddressPostCodeTest {
    JacksonTester<AddressPostCode> json;

    private ObjectMapper mapper;

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JacksonTester.initFields(this, mapper);
        assert json != null; // required for FindBugs NP_UNWRITTEN_FIELD
    }

    private String createResourceAddressJsonString() {
        return ""
                + "{"
                + "  'prefecture_code': '05',"
                + "  'prefecture': '秋田県',"
                + "  'prefecture_kana': 'ｱｷﾀｹﾝ',"
                + "  'city_kana': 'ﾕｻﾞﾜｼ',"
                + "  'city': '湯沢市',"
                + "  'code': '05207',"
                + "  'area_kana': 'ｶｯｸｲｻﾜﾔﾏ',"
                + "  'area': 'カツクイ沢山',"
                + "  'koaza_area': 0,"
                + "  'chome_area': 0,"
                + "  'multi_post_area': 0,"
                + "  'post_code': '0120833',"
                + "  'old_post_code': '012',"
                + "  'multi_area': 0,"
                + "  'update_show': 0,"
                + "  'change_reason': 0"
                + "}";
    }

    @Test
    public void testOK() {
        // setup
        AddressPostCode sut = AddressPostCodeFixtures.createAddressPostCode();
        // exercise
        Set<ConstraintViolation<AddressPostCode>> actual = validator.validate(sut);
        // verify
        assertThat(actual).hasSize(0);
    }

    @Test
    public void testNotNullConstraints() {
        // setup
        AddressPostCode sut = new AddressPostCode();
        // exercise
        Set<ConstraintViolation<AddressPostCode>> actual = validator.validate(sut);
        // verify
        assertThat(actual).hasSize(0);
    }
}
