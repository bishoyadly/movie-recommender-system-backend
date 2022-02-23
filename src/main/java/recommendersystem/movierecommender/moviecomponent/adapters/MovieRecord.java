package recommendersystem.movierecommender.moviecomponent.adapters;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@TypeDef(name = "json", typeClass = JsonStringType.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity(name = "movies_metadata")
@Getter
@Setter
class MovieRecord {
    @Column(name = "id")
    @Id
    private UUID id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "original_title")
    private String originalTitle;

    @Type(type = "json")
    @Column(name = "genres", columnDefinition = "json")
    private List<JsonRecord> genres;

    @Column(name = "vote_average", precision = 131089)
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "popularity", precision = 131089)
    private BigDecimal popularity;

    @Column(name = "movie_img_url")
    private String movieImgUrl;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "original_language")
    private String originalLanguage;

    @Type(type = "json")
    @Column(name = "production_companies", columnDefinition = "json")
    private List<JsonRecord> productionCompanies;

    @Type(type = "json")
    @Column(name = "production_countries", columnDefinition = "json")
    private List<JsonRecord> productionCountries;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "adult")
    private String adult;

    @Column(name = "belongs_to_collection")
    private String belongsToCollection;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "overview")
    private String overview;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime", precision = 131089)
    private BigDecimal runtime;

    @Column(name = "spoken_languages")
    private String spokenLanguages;

    @Column(name = "status")
    private String status;

    @Column(name = "video")
    private String video;

}


