package fr.cailliaud.rsl.restapi.service.mapper;


import fr.cailliaud.rsl.restapi.domain.Champion;
import fr.cailliaud.rsl.restapi.service.dto.ChampionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChampionMapper {

    public Champion fromChampionDTO(ChampionDTO championDTO) {
        if(Objects.isNull(championDTO)){
            return null;
        }
        Champion champion = new Champion();
        champion.setName(championDTO.getName().toLowerCase());
        champion.setAura(championDTO.getAura());
        return champion;
    }

    public ChampionDTO fromChampion(Champion champion){
        if(Objects.isNull(champion)){
            return null;
        }
        ChampionDTO championDTO = new ChampionDTO();
        championDTO.setName(champion.getName());
        championDTO.setAura(champion.getAura());
        return championDTO;
    }

    public List<Champion> fromChampionDTOList(List<ChampionDTO> championDTOList){
        return championDTOList.stream()
                .filter(Objects::nonNull)
                .map(this::fromChampionDTO)
                .collect(Collectors.toList());
    }

    public List<ChampionDTO> fromChampionList(List<Champion> championList){
        return championList.stream()
                .filter(Objects::nonNull)
                .map(this::fromChampion)
                .collect(Collectors.toList());
    }
}
