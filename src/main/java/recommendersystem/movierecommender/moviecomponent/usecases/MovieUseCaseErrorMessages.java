package recommendersystem.movierecommender.moviecomponent.usecases;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieUseCaseErrorMessages {
    public static final String INVALID_MOVIE_ID = "Invalid Movie Id";
    public static final String MOVIE_NOT_FOUND = "Movie not found";
    public static final String INVALID_PAGE_SIZE = "Invalid Page Size : must be equal or greater than 5";
    public static final String INVALID_PAGE_NUMBER = "Invalid Page Number : must be greater than zero";
}
