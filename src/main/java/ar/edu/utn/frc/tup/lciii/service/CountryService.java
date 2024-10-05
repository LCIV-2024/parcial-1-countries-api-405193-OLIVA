package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.PostDTO;
import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class CountryService {

        @Autowired
        private CountryRepository countryRepository;

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private ObjectMapper objectMapper;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }

        public List<CountryDTO> getCountriesDTO(String code, String name){
                List<CountryDTO> countryDTOList = new ArrayList<>();
                List<Country> countryList = this.getAllCountries();

                if (code != null){
                        for (Country country : countryList){
                                if (country.getCode().equals(code))
                                        countryDTOList.add(this.mapToDTO(country));
                        }
                }
                else if (name != null){
                        for (Country country : countryList){
                                if (country.getCode().equals(name))
                                        countryDTOList.add(this.mapToDTO(country));
                        }
                }
                return countryDTOList;
        }

        public List<CountryDTO> getCountriesDTOByContinent(String continent){
                List<CountryDTO> countryDTOList = new ArrayList<>();
                List<Country> countryList = this.getAllCountries();

                for (Country country : countryList){
                        if (country.getRegion() != null &&
                        country.getRegion().equals(continent)) {
                                countryDTOList.add(this.mapToDTO(country));
                        }
                }
                return countryDTOList;
        }

        public List<CountryDTO> getCountriesDTOByLanguage(String language){
                List<CountryDTO> countryDTOList = new ArrayList<>();
                List<Country> countryList = this.getAllCountries();

                for (Country country : countryList){
                        if (country.getLanguages() != null &&
                        country.getLanguages().containsValue(language)) {
                                countryDTOList.add(this.mapToDTO(country));
                        }
                }
                return countryDTOList;
        }

        public List<CountryDTO> getCountriesDTOByMostBorders() {
                List<CountryDTO> countryDTOList = new ArrayList<>();
                List<Country> countryList = this.getAllCountries();

                for (Country country : countryList){
                        if (country.getBorders() != null &&
                        country.getBorders().size() > 5) {
                                countryDTOList.add(this.mapToDTO(country));
                        }
                }
                return countryDTOList;
        }

        public List<CountryDTO> post(PostDTO amountOfCountryToSave) {
                List<Country> countryList = this.getAllCountries();
                List<CountryDTO> countryDTOList = new ArrayList<>();
                List<CountryEntity> countriesToSave = new ArrayList<>();

                for (int i = 0; i < amountOfCountryToSave.getAmountOfCountryToSave() &&
                        i < countryList.size(); i++) {
                        countryDTOList.add(mapToDTO(countryList.get(i)));
                        CountryEntity countryEntity = new CountryEntity();
                        countryEntity.setArea(countryList.get(i).getArea());
                        countryEntity.setName(countryList.get(i).getName());
                        countryEntity.setCode(countryList.get(i).getCode());
                        countryEntity.setPopulation(countryList.get(i).getPopulation());
                        countriesToSave.add(countryEntity);
                }

                countryRepository.saveAll(countriesToSave);

                return countryDTOList;
        }
}