package academy.devdojo.springboot2.Service;

import academy.devdojo.springboot2.Mapper.AnimeMapper;
import academy.devdojo.springboot2.Repository.AnimeRepository;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimesPostRequestBody;
import academy.devdojo.springboot2.requests.AnimesPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> ListAll(){
        // A LISTA EM SI
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestexception(long id){         // ENCONTRA O ID DO ANIME PARA RETORNAR O CONTEUDO + ID
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    //CRIA UM NOVO ID E RECEBE UMA NOVA STRING COM OUTRO ANIME
    public Anime save(AnimesPostRequestBody animesPostRequestBody){
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animesPostRequestBody));
    }

    //DELETA UM ID JUNTAMENTE COM O SEU CONTEUDO
    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestexception(id));
    }

    //DELETA E SUBSTITUI UM DETERMINADO ID JUNTAMENTE COM SEU CONTEUDO
    public void replace(AnimesPutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestexception(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);

        anime.setId(savedAnime.getId());

        animeRepository.save(anime);
    }
}
