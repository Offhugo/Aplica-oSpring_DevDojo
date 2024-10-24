package academy.devdojo.springboot2.requests;

import lombok.Data;

@Data
public class AnimesPutRequestBody {
    private Long id;
    private String nome;
}
