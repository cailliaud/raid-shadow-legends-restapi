package fr.cailliaud.rsl.restapi.web.api;

import fr.cailliaud.rsl.restapi.domain.Champion;
import fr.cailliaud.rsl.restapi.service.ChampionService;
import fr.cailliaud.rsl.restapi.service.dto.ChampionDTO;
import fr.cailliaud.rsl.restapi.service.dto.ChampionUpdateDTO;
import fr.cailliaud.rsl.restapi.service.dto.StatsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/champions")
@Tag(name = "Champion", description = "API pour les champions")
public class ChampionResource {

    private ChampionService championService;

    public ChampionResource(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ChampionDTO>> getAllChampions() {
        final List<ChampionDTO> champions = championService.getAllManagedChampions();
        return new ResponseEntity<>(champions, HttpStatus.OK);
    }

    @GetMapping("/{championName}")
    @Operation(summary = "Get champion by champion name",
            responses = {
                    @ApiResponse(description = "The champion",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChampionDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Champion not found")})
    public ResponseEntity<ChampionDTO> getChampion( @PathVariable String championName) {
        ChampionDTO championDTO =this.championService.getChampion(championName);
        if (Objects.isNull(championDTO)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Champion not found");
        }
        return new ResponseEntity<>(championDTO,HttpStatus.OK);

    }

    @PostMapping("/")
    @Operation(summary = "Create champion", security = @SecurityRequirement(name = "basicAuth"),
        responses = {
                @ApiResponse(responseCode = "201", description = "The champion has been created",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Champion.class))),
                @ApiResponse(responseCode = "409", description = "The champion provided already exists")}
    )
    public ResponseEntity<Champion> createChampion(@Valid @RequestBody ChampionDTO championDTO) throws URISyntaxException {

        log.debug("REST request to save champion : {}", championDTO);
        Champion existingChampion = this.championService.findChampionByName(championDTO.getName());
        if(existingChampion != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Champion already exists");
        }
        Champion newChampion = championService.createChampion(championDTO);
        return ResponseEntity.created(new URI("/api/champions/" + newChampion.getName()))
                .body(newChampion);
    }

    @PatchMapping("/{championName}")
    @Operation(summary = "Update champion metadata",security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "The champion",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Champion.class))),
                    @ApiResponse(responseCode = "404", description = "The champion name provided does not match any existing champion.")})
    public ResponseEntity<Champion> updateChampion(
            @PathVariable String championName, @Valid @RequestBody ChampionUpdateDTO championUpdateDTO){
        log.debug("REST request to update champion {} : {}", championName, championUpdateDTO);


        Champion existingChampion = this.championService.findChampionByName(championName);
        if (Objects.isNull(existingChampion)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Champion not found");
        }
        championService.updateChampion(existingChampion, championUpdateDTO);
        return new ResponseEntity<>(existingChampion, HttpStatus.OK);
    }

    @PostMapping("/{championName}/stats")
    @Operation(summary = "Configure champion stats", security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity setChampionSkills(
            @PathVariable String championName, @Valid @RequestBody StatsDTO statsDTO ){
        Champion existingChampion = this.championService.findChampionByName(championName);
        if (Objects.isNull(existingChampion)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Champion not found");
        }

        championService.updateChampionSkills(existingChampion, statsDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


}
