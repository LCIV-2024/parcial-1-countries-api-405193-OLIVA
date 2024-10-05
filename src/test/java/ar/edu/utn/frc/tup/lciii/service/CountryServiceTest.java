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

    @Test
    void getCountriesDTO() {
        List<Country> countries = new ArrayList<>();

        countries.add(Country.builder()
                .name("Argentina")
                .population(45)
                .area(1)
                .region("Americas")
                .languages(null)
                .code("ARG")
                .borders(null)
                .build());

        when(countryService.getAllCountries()).thenReturn(countries);

        List<CountryDTO> countryDTOList = countryService.getCountriesDTO("ARG", null);

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