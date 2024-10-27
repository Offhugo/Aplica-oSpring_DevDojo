package academy.devdojo.springboot2.Mapper;

import academy.devdojo.springboot2.Service.AnimeService;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimesPostRequestBody;
import academy.devdojo.springboot2.requests.AnimesPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "Spring")
public abstract class AnimeMapper {
    public static AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimesPostRequestBody animesPostRequestBodyRequestBody);

    public abstract Anime toAnime(AnimesPutRequestBody animesPutRequestBodyRequestBody);

}
