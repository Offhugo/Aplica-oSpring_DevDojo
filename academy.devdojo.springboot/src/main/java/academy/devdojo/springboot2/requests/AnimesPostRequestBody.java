package academy.devdojo.springboot2.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class AnimesPostRequestBody {
    @NotEmpty(message = "Deve existir algum valor")
    private String nome;

    @URL(message = "This URL is not valid")
    private String url;
}
