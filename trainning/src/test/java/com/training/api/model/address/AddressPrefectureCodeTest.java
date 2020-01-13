package com.training.api.model.address;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Test for {@link AddressPrefectureCode}
 */
public class AddressPrefectureCodeTest {
    JacksonTester<AddressPrefectureCode> json;

    private ObjectMapper mapper;

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JacksonTester.initFields(this, mapper);
        assert json != null; // required for FindBugs NP_UNWRITTEN_FIELD
    }

    private String createResourceCityJsonString() {
        return ""
                + "{"
                + "	 'prefecture_code': '05',"
                + "	 'prefecture': '秋田県',"
                + "	 'prefecture_kana': 'ｱｷﾀｹﾝ',"
                + "	 'city_kana': 'ﾕｻﾞﾜｼ',"
                + "	 'city': '湯沢市',"
                + "	 'code': '05207'"
                + "}";
    }

    @Test
    public void testOK() {
        // setup
        List<AddressPrefectureCode> sut = AddressPrefectureCodeFixture.createAddressPrefectureCode();
        // exercise
        Set<ConstraintViolation<List<AddressPrefectureCode>>> actual = validator.validate(sut);
        // verify
        assertThat(actual).hasSize(0);
    }

    @Test
    public void testNotNullConstraints() {
        // setup
        AddressPrefectureCode sut = new AddressPrefectureCode();
        // exercise
        Set<ConstraintViolation<AddressPrefectureCode>> actual = validator.validate(sut);
        // verify
        assertThat(actual).hasSize(0);
    }
}