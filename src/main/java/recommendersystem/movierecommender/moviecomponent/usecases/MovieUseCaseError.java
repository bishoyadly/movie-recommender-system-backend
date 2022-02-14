package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieUseCaseError {

    public MovieUseCaseError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;
    private String errorCause;
}
