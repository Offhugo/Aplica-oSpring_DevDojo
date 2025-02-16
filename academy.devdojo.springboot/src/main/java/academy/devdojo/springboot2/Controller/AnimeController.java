package academy.devdojo.springboot2.Controller;

import academy.devdojo.springboot2.Service.AnimeService;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimesPostRequestBody;
import academy.devdojo.springboot2.requests.AnimesPutRequestBody;
import academy.devdojo.springboot2.util.DataUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    @Autowired
    private final DataUtil datautil;
    private final AnimeService animeService;

    @GetMapping
    @Transactional
    public List<Anime> list(){
        log.info(datautil.formatLocalDataTimeToDatabasestyle(LocalDateTime.now()));
        return animeService.ListAll();
    }

    @GetMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestexception(id));
    }

    @GetMapping(path = "/find")
    @Transactional
    public ResponseEntity<List<Anime>> findByNome(@Valid @RequestParam(required = false) String nome /*@RequestParam(defaultValue = "null") long id*/){
        return ResponseEntity.ok(animeService.findByNome(nome));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimesPostRequestBody animesPostRequestBody){
        return new ResponseEntity<>(animeService.save(animesPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> replace(@RequestBody @Valid AnimesPutRequestBody animesPutRequestBody) {
        animeService.replace(animesPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
