package ar.edu.utn.frc.tup.lciii.dtos.common;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @Max(10)
    private Integer amountOfCountryToSave;
}
