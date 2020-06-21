package fr.cailliaud.rsl.restapi.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ChampionUpdateDTO {

    @Size(min = 1, max = 150)
    private String aura;

}
