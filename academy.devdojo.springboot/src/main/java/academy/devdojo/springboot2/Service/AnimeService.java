package academy.devdojo.springboot2.Service;

import academy.devdojo.springboot2.Exception.BadRequestException;
import academy.devdojo.springboot2.Mapper.AnimeMapper;
import academy.devdojo.springboot2.Repository.AnimeRepository;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimesPostRequestBody;
import academy.devdojo.springboot2.requests.AnimesPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AnimeRepository")
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Transactional
    public Page<Anime> ListAll(Pageable pageable){
        // A LISTA EM SI
        return animeRepository.findAll(pageable);
    }

    @Transactional
    public List<Anime> findByNome(String nome){
        return animeRepository.findByNome(nome);
    }

    // note que para o handler funcionar, aqui devemos fazer a exceção condizer com o handler
    public Anime findByIdOrThrowBadRequestexception(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
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
