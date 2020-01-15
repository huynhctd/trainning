package com.training.api.model.address;

import com.training.api.model.area.Area;
import com.training.api.model.area.AreaFixtures;
import com.training.api.model.area.AreaRepository;
import com.training.api.model.city.City;
import com.training.api.model.city.CityFixtures;
import com.training.api.model.city.CityRepository;
import jp.xet.sparwings.spring.web.httpexceptions.HttpNotFoundException;
import org.junit.Before;
 import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Test for {@link AddressService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {
    @Mock
    AreaRepository areaRepository;

    @Mock
    CityRepository cityRepository;

    AddressService sut;

    @Before
    public void setUp() {
        sut = new AddressService(areaRepository,cityRepository);
    }

    /**
     * Test post code
     */
    @Test
    public void findByPostCode() {
        // setup
        Area area = AreaFixtures.createArea();
        List<Area> listArea = new ArrayList<>();
        AddressPostCode addressPostCode = AddressPostCodeFixtures.createAddressPostCode();
        listArea.add(area);
        when(areaRepository.findByPostPostCode(area.getPost().getPostCode())).thenReturn(listArea);

        // exercise
        List<AddressPostCode> actual = sut.findByPostCode("012-083-3");

        // verify
        verify(areaRepository, times(1)).findByPostPostCode(area.getPost().getPostCode());
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0)).isEqualTo(addressPostCode);
    }

    /**
     * Test post code throws HttpNotFoundException
     */
    @Test
    public void findByPostCodeThrowsHFE(){
        // exercise
        assertThatThrownBy(()-> sut.findByPostCode("10-000-00")).isInstanceOf(HttpNotFoundException.class)
                .hasMessage("PostCode not found");
    }

    /**
     * Test post code throws NullPointerException
     */
    @Test
    public void findByPostCodeThrowsNPE(){
        // exercise
        assertThatThrownBy(()-> sut.findByPostCode(null)).isInstanceOf(NullPointerException.class)
                .hasMessage("Code must be not null");
    }

    /**
     * Test post code throws IllegalArgumentException
     */
    @Test
    public void findByPostCodeTestThrowsIAE(){
        // exercise
        assertThatThrownBy(()-> sut.findByPostCode("abc")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Code format fail");
    }

    /**
     * Test prefecture code
     */
    @Test
    public void findByPrefectureCode() {
        // setup
        City city = CityFixtures.createCity();
        List<City> listCity = new ArrayList<>();
        listCity.add(city);
        AddressPrefectureCode addressPrefectureCode = AddressPrefectureCodeFixture.createAddressPrefectureCode();
        when(cityRepository.findByPrefecturePrefectureCode(city.getPrefecture().getPrefectureCode())).thenReturn(listCity);

        // exercise
        List<AddressPrefectureCode> actual = sut.findByPrefectureCode(city.getPrefecture().getPrefectureCode());

        // verify
        verify(cityRepository, times(1)).findByPrefecturePrefectureCode(city.getPrefecture().getPrefectureCode());
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0)).isEqualTo(addressPrefectureCode);
    }

    /**
     * Test prefecture code throws HttpNotFoundException
     * Input
     *      prefectureCode = 9999
     * Output:
     *      *    throws HttpNotFoundException
     */
    @Test
    public void findByPrefectureCodeThrowsHFE(){
        // exercise
        assertThatThrownBy(()-> sut.findByPrefectureCode("9999")).isInstanceOf(HttpNotFoundException.class);
    }

    /**
     * Test prefecture code throws NullPointerException
     * Input
     *      prefectureCode = Null
     * Output:
     *      *    throws NullPointerException
     */
    @Test
    public void findByPrefectureCodeThrowsNPE(){
        // exercise
        assertThatThrownBy(()-> sut.findByPrefectureCode(null)).isInstanceOf(NullPointerException.class)
                .hasMessage("Code must be not null");
    }

    /**
     * Test prefecture code throws IllegalArgumentException
     * Input
     *      prefectureCode = abc
     * Output:
     *      *    throws IllegalArgumentException
     */
    @Test
    public void findByPrefectureCodeThrowsIAE1(){
        // exercise
        assertThatThrownBy(()-> sut.findByPrefectureCode("abc")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Code must be half size number");
    }

    /**
     * Test prefecture code throws IllegalArgumentException
     * Input
     *      prefectureCode = a10
     * Output:
     *      *    throws IllegalArgumentException
     */
    @Test
    public void findByPrefectureCodeThrowsIAE2(){
        // exercise
        assertThatThrownBy(()-> sut.findByPrefectureCode("a10")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Code must be half size number");
    }

    /**
     * Test prefecture code throws IllegalArgumentException
     * Input
     *      prefectureCode = 1*1
     * Output:
     *      *    throws IllegalArgumentException
     */
    @Test
    public void findByPrefectureCodeThrowsIAE3(){
        // exercise
        assertThatThrownBy(()-> sut.findByPrefectureCode("1*1")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Code must be half size number");
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = -12-12-123
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode1_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("-12-12-123-"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 12-12-123-
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode2_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("12-12-123-"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 12-12--123
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode3_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("12-12--123"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 10-000-0
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode4_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("10-000-0"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 10-000-000
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode5_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("10-000-000"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 012/083*3
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode6_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("012/083*3"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 012a083-3
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode7_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("012a083-3"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 012 083-3
     * Output:
     *    throws IllegalArgumentException
     */
    @Test
    public void testSearchByPostCode_InvalidFormatOfPostCode8_ThrowsHBRE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("012 083-3"));

        // verify
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test search by post code
     * Input:
     *    postCode = 0-1-2-0-8-1-1
     * Output:
     *    throws HttpNotFoundException
     */
    @Test
    public void testSearchByPostCode_PostCodeIsNotExist_ThrowsHNFE(){
        // exercise
        Throwable thrown = catchThrowable(()-> sut.findByPostCode("0-1-2-0-8-1-1"));

        // verify
        assertThat(thrown).isInstanceOf(HttpNotFoundException.class);
    }
}
