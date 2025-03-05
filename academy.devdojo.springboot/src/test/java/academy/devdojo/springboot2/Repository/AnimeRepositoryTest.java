package academy.devdojo.springboot2.Repository;

import academy.devdojo.springboot2.domain.Anime;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    // teste envolvendo a criação efetiva do anime
    @Test
    @DisplayName("save created Anime when successfull")
    void save_CreatedAnime_WhenSuccefull(){
        Anime animeToSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getNome()).isEqualTo(animeToSaved.getNome());
    }

    // Teste envolvendo a atualização do anime já criado
    @Test
    @DisplayName("Save update anime when succefull")
    void save_UpdateAnime_WhenSuccefull(){
        Anime animeToSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToSaved);

        animeSaved.setNome("Dr.Stone");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getNome()).isEqualTo(animeToSaved.getNome());
    }

    // Teste envolvendo a remoção do anime criado
    @Test
    @DisplayName("Delete removes anime when succefull")
    void delete_DeleteAnime_WhenSuccefull(){
        Anime animeToSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToSaved);

        this.animeRepository.delete(animeSaved);

        this.animeRepository.findById(animeSaved.getId());

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();

    }

    // Teste envolvendo a busca da lista de animes
    @Test
    @DisplayName("Find by name returns list anime when succefull")
    void findByName_ReturnsListOfAnime_WhenSuccefull(){
        Anime animeToSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToSaved);

        String nome = animeSaved.getNome();

       List<Anime> animes = this.animeRepository.findByNome(nome);

       Assertions.assertThat(animes).isNotEmpty();
       Assertions.assertThat(animes).contains(animeSaved);
    }

    // Teste envolvendo a garantia sobre retorno de valor nulo da lista
    @Test
    @DisplayName("Find by name returns to empty list when no anime is found")
    void findByName_ReturnsToEMptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByNome("Black Cover");

        Assertions.assertThat(animes)
                .isNotEmpty();
    }

    // teste envolvendo a criação efetiva do anime
    @Test
    @DisplayName("save throw ConstraintViolationException when name is empy")
    void save_throwConstraintViolationException_whenNameIsEmpy(){
        Anime anime = new Anime();

        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);


    }

    // Anime original criado
    private Anime createAnime(){
        return Anime.builder()
                .nome("Hellsing")
                .build();
    }

}