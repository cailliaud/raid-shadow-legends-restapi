package fr.cailliaud.rsl.restapi.service;

import fr.cailliaud.rsl.restapi.domain.Champion;
import fr.cailliaud.rsl.restapi.domain.ChampionStats;
import fr.cailliaud.rsl.restapi.repository.ChampionRepository;
import fr.cailliaud.rsl.restapi.service.dto.ChampionDTO;
import fr.cailliaud.rsl.restapi.service.dto.ChampionUpdateDTO;
import fr.cailliaud.rsl.restapi.service.dto.StatsDTO;
import fr.cailliaud.rsl.restapi.service.mapper.ChampionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChampionService {

    private ChampionRepository championRepository;
    private ChampionMapper championMapper;

    public ChampionService(ChampionRepository championRepository, ChampionMapper championMapper){
        this.championRepository=championRepository;
        this.championMapper=championMapper;
    }


    public List<ChampionDTO> getAllManagedChampions() {
        return championMapper.fromChampionList(championRepository.findAll());
    }

    public ChampionDTO getChampion(String name){
        return this.championMapper.fromChampion(this.findChampionByName(name));
    }

    public Champion findChampionByName(String name){
        Optional<Champion> optionalChampion = championRepository.findOneByName(name.toLowerCase());
        if(optionalChampion.isEmpty()){
            return null;
        }else{
            return optionalChampion.get();
        }
    }

    public Champion createChampion(ChampionDTO championDTO) {
        championRepository.findOneByName(championDTO.getName().toLowerCase()).ifPresent(this::removeChampion);
        Champion newChampion = championMapper.fromChampionDTO(championDTO);
        championRepository.save(newChampion);
        log.debug("Created Information for User: {}", newChampion);
        return newChampion;
    }

    private void removeChampion(Champion existingUser) {
        championRepository.delete(existingUser);
    }

    public void updateChampion(Champion champion, ChampionUpdateDTO championUpdateDTO) {
        if(championUpdateDTO.getAura() != null){
            champion.setAura(championUpdateDTO.getAura());
        }
        championRepository.save(champion);
    }

    public void updateChampionSkills(Champion existingChampion, StatsDTO statsDTO) {

        ChampionStats stats = new ChampionStats();
        stats.setAccuracy(statsDTO.getAccuracy());
        stats.setAttack(statsDTO.getAttack());
        stats.setDefense(statsDTO.getDefense());
        stats.setCriticalDamage(statsDTO.getCriticalDamage());
        stats.setCriticalRate(statsDTO.getCriticalRate());
        stats.setSpeed(statsDTO.getSpeed());
        stats.setHealthPoints(statsDTO.getHealthPoints());

        existingChampion.setStats(stats);

        this.championRepository.save(existingChampion);

    }
}
