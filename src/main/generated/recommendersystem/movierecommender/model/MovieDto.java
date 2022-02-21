package recommendersystem.movierecommender.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * MovieDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MovieDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("title")
    private String title;

    @JsonProperty("genresList")
    @Valid
    private List<GenreDto> genresList = null;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("rating")
    private Float rating;

    @JsonProperty("ratingsCount")
    private Integer ratingsCount;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("originalLanguage")
    private String originalLanguage;

    @JsonProperty("releaseDate")
    private String releaseDate;

    @JsonProperty("productionCountries")
    @Valid
    private List<String> productionCountries = null;

    @JsonProperty("productionCompanies")
    @Valid
    private List<String> productionCompanies = null;

    public MovieDto id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */
    @ApiModelProperty(value = "")

    @Valid

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MovieDto imdbID(String imdbID) {
        this.imdbID = imdbID;
        return this;
    }

    /**
     * Get imdbID
     *
     * @return imdbID
     */
    @ApiModelProperty(value = "")


    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public MovieDto title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     */
    @ApiModelProperty(value = "")


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieDto genresList(List<GenreDto> genresList) {
        this.genresList = genresList;
        return this;
    }

    public MovieDto addGenresListItem(GenreDto genresListItem) {
        if (this.genresList == null) {
            this.genresList = new ArrayList<>();
        }
        this.genresList.add(genresListItem);
        return this;
    }

    /**
     * Get genresList
     *
     * @return genresList
     */
    @ApiModelProperty(example = "[\"ACTION\",\"COMEDY\"]", value = "")

    @Valid

    public List<GenreDto> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<GenreDto> genresList) {
        this.genresList = genresList;
    }

    public MovieDto imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    /**
     * Get imageUrl
     *
     * @return imageUrl
     */
    @ApiModelProperty(example = "http://movie-image-url.jpg", value = "")


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MovieDto rating(Float rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Get rating
     *
     * @return rating
     */
    @ApiModelProperty(example = "8.7", value = "")


    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public MovieDto ratingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
        return this;
    }

    /**
     * Get ratingsCount
     *
     * @return ratingsCount
     */
    @ApiModelProperty(example = "100", value = "")


    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public MovieDto overview(String overview) {
        this.overview = overview;
        return this;
    }

    /**
     * Get overview
     *
     * @return overview
     */
    @ApiModelProperty(example = "movie overview", value = "")


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public MovieDto originalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    /**
     * Get originalLanguage
     *
     * @return originalLanguage
     */
    @ApiModelProperty(example = "english", value = "")


    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public MovieDto releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * Get releaseDate
     *
     * @return releaseDate
     */
    @ApiModelProperty(example = "10-10-2010", value = "")


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieDto productionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
        return this;
    }

    public MovieDto addProductionCountriesItem(String productionCountriesItem) {
        if (this.productionCountries == null) {
            this.productionCountries = new ArrayList<>();
        }
        this.productionCountries.add(productionCountriesItem);
        return this;
    }

    /**
     * Get productionCountries
     *
     * @return productionCountries
     */
    @ApiModelProperty(example = "[\"USA\",\"France\"]", value = "")


    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public MovieDto productionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
        return this;
    }

    public MovieDto addProductionCompaniesItem(String productionCompaniesItem) {
        if (this.productionCompanies == null) {
            this.productionCompanies = new ArrayList<>();
        }
        this.productionCompanies.add(productionCompaniesItem);
        return this;
    }

    /**
     * Get productionCompanies
     *
     * @return productionCompanies
     */
    @ApiModelProperty(example = "[\"Marvel\",\"Disney\"]", value = "")


    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movie = (MovieDto) o;
        return Objects.equals(this.id, movie.id) &&
                Objects.equals(this.imdbID, movie.imdbID) &&
                Objects.equals(this.title, movie.title) &&
                Objects.equals(this.genresList, movie.genresList) &&
                Objects.equals(this.imageUrl, movie.imageUrl) &&
                Objects.equals(this.rating, movie.rating) &&
                Objects.equals(this.ratingsCount, movie.ratingsCount) &&
                Objects.equals(this.overview, movie.overview) &&
                Objects.equals(this.originalLanguage, movie.originalLanguage) &&
                Objects.equals(this.releaseDate, movie.releaseDate) &&
                Objects.equals(this.productionCountries, movie.productionCountries) &&
                Objects.equals(this.productionCompanies, movie.productionCompanies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbID, title, genresList, imageUrl, rating, ratingsCount, overview, originalLanguage, releaseDate, productionCountries, productionCompanies);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDto {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    imdbID: ").append(toIndentedString(imdbID)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    genresList: ").append(toIndentedString(genresList)).append("\n");
        sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
        sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
        sb.append("    ratingsCount: ").append(toIndentedString(ratingsCount)).append("\n");
        sb.append("    overview: ").append(toIndentedString(overview)).append("\n");
        sb.append("    originalLanguage: ").append(toIndentedString(originalLanguage)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    productionCountries: ").append(toIndentedString(productionCountries)).append("\n");
        sb.append("    productionCompanies: ").append(toIndentedString(productionCompanies)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

