package fr.cailliaud.rsl.restapi.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ChampionDTO {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    private String aura = "No aura description";


}
