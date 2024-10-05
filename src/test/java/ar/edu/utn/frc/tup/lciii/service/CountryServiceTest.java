package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryServiceTest {

    @SpyBean
    private CountryService countryService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getAllCountries() {
    }


    void getCountriesDTO() {

        List<CountryDTO>countryDTOList = new ArrayList<>();
        CountryDTO countryDTO = new CountryDTO("ARG", "Argentina");
        countryDTOList.add(countryDTO);

        when(countryService.getCountriesDTO("ARG", null)).thenReturn(countryDTOList);

        assertEquals(1, countryDTOList.size());
    }

    @Test
    void getCountriesDTOByContinent() {
    }

    @Test
    void getCountriesDTOByLanguage() {
    }

    @Test
    void getCountriesDTOByMostBorders() {
    }

    @Test
    void post() {
    }
}