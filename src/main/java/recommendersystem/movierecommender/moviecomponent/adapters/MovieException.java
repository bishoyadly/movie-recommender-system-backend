package recommendersystem.movierecommender.moviecomponent.adapters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MovieException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public MovieException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
