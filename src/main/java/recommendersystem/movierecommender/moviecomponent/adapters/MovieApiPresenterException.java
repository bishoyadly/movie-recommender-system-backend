package recommendersystem.movierecommender.moviecomponent.adapters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import recommendersystem.movierecommender.model.ProblemDto;

@Getter
@Setter
public class MovieApiPresenterException extends ResponseStatusException {

    private final ProblemDto problemDto;

    public MovieApiPresenterException(HttpStatus httpStatus, String errorMessage) {
        super(httpStatus, errorMessage);
        this.problemDto = new ProblemDto();
        this.problemDto.setErrorMessage(httpStatus.toString());
        this.problemDto.setErrorDetail(errorMessage);
    }
}
