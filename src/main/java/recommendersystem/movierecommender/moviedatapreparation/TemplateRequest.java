package recommendersystem.movierecommender.moviedatapreparation;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TemplateRequest {
    private UUID movieId;
    private String name;
    private String body;
}
