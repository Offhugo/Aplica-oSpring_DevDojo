package academy.devdojo.springboot2.Mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.Anime.AnimeBuilder;
import academy.devdojo.springboot2.requests.AnimesPostRequestBody;
import academy.devdojo.springboot2.requests.AnimesPutRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-15T15:49:58-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AnimeMapperImpl extends AnimeMapper {

    @Override
    public Anime toAnime(AnimesPostRequestBody animesPostRequestBodyRequestBody) {
        if ( animesPostRequestBodyRequestBody == null ) {
            return null;
        }

        AnimeBuilder anime = Anime.builder();

        anime.nome( animesPostRequestBodyRequestBody.getNome() );

        return anime.build();
    }

    @Override
    public Anime toAnime(AnimesPutRequestBody animesPutRequestBodyRequestBody) {
        if ( animesPutRequestBodyRequestBody == null ) {
            return null;
        }

        AnimeBuilder anime = Anime.builder();

        anime.id( animesPutRequestBodyRequestBody.getId() );
        anime.nome( animesPutRequestBodyRequestBody.getNome() );

        return anime.build();
    }
}
