package recommendersystem.movierecommender.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "movies_metadata")
@Getter
@Setter
public class MoviesMetadatum {

    @Column(name = "adult")
    private String adult;

    @Column(name = "belongs_to_collection")
    private String belongsToCollection;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "genres")
    private String genres;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity", precision = 131089)
    private BigDecimal popularity;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "production_companies")
    private String productionCompanies;

    @Column(name = "production_countries")
    private String productionCountries;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime", precision = 131089)
    private BigDecimal runtime;

    @Column(name = "spoken_languages")
    private String spokenLanguages;

    @Column(name = "status")
    private String status;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "title")
    private String title;

    @Column(name = "video")
    private String video;

    @Column(name = "vote_average", precision = 131089)
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "movie_img_url")
    private String movieImgUrl;

}