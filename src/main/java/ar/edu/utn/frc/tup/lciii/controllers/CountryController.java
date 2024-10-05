package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.PostDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/countries")
public class CountryController {

    @Autowired
    private final CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<CountryDTO>> getCountries(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ){
        return ResponseEntity.ok(countryService.getCountriesDTO(code, name));
    }

    @GetMapping("/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getCountriesByContinent(
            @PathVariable String continent
    ){
        return ResponseEntity.ok(countryService.getCountriesDTOByContinent(continent));
    }

    @GetMapping("/{language}/language")
    public ResponseEntity<List<CountryDTO>> getCountriesByLanguage(
            @PathVariable String language
    ){
        List<CountryDTO> countryDTOList = countryService.getCountriesDTOByLanguage(language);
        return ResponseEntity.ok(countryDTOList);
    }

    @GetMapping("/most-borders")
    public ResponseEntity<List<CountryDTO>> getCountriesByMostBorders(){
        List<CountryDTO> countryDTOList = countryService.getCountriesDTOByMostBorders();
        return ResponseEntity.ok(countryDTOList);
    }

    @PostMapping()
    public ResponseEntity<List<CountryDTO>> getCountries(
            @RequestBody PostDTO amountOfCountryToSave
            ){
        return ResponseEntity.ok(countryService.post(amountOfCountryToSave));
    }
}