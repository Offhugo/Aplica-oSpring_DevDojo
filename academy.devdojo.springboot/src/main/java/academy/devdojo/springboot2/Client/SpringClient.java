package academy.devdojo.springboot2.Client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        // Requisição GET
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 7);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 7);

        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);

        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all"
                , HttpMethod.GET
                , null,
                new ParameterizedTypeReference<List<Anime>>() {
                }
        );

        log.info(exchange.getBody());

        // Requisição POST

//        Anime kingdom = Anime.builder().nome("kingdom").build();
//        Anime kingdomSave = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//        log.info("Saved anime {}", kingdomSave);

        Anime samuraiChamploo = Anime.builder().nome("samuraiChamploo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes"
                , HttpMethod.POST
                , new HttpEntity<>(samuraiChamploo),
                Anime.class);

        log.info("saved anime {}", samuraiChamplooSaved);

        Anime animeSaved = samuraiChamplooSaved.getBody();
        animeSaved.setNome("Samurai Champloo 2");

        ResponseEntity<Anime> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes"
                , HttpMethod.POST
                , new HttpEntity<>(samuraiChamploo),
                Anime.class);

//        private static HttpHeaders createJsonHeader() {
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//            return httpHeaders;
//        }
    }
}
