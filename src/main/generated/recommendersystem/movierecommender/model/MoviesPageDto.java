package recommendersystem.movierecommender.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MoviesPageDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MoviesPageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("pageNumber")
    private Integer pageNumber;

    @JsonProperty("elementsNumber")
    private Integer elementsNumber;

    @JsonProperty("totalElements")
    private Float totalElements;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("content")
    @Valid
    private List<MovieDto> content = null;

    public MoviesPageDto pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Get pageSize
     *
     * @return pageSize
     */
    @ApiModelProperty(value = "")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public MoviesPageDto pageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Get pageNumber
     *
     * @return pageNumber
     */
    @ApiModelProperty(value = "")


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public MoviesPageDto elementsNumber(Integer elementsNumber) {
        this.elementsNumber = elementsNumber;
        return this;
    }

    /**
     * Get elementsNumber
     *
     * @return elementsNumber
     */
    @ApiModelProperty(value = "")


    public Integer getElementsNumber() {
        return elementsNumber;
    }

    public void setElementsNumber(Integer elementsNumber) {
        this.elementsNumber = elementsNumber;
    }

    public MoviesPageDto totalElements(Float totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    /**
     * Get totalElements
     *
     * @return totalElements
     */
    @ApiModelProperty(value = "")


    public Float getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Float totalElements) {
        this.totalElements = totalElements;
    }

    public MoviesPageDto totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * Get totalPages
     *
     * @return totalPages
     */
    @ApiModelProperty(value = "")


    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MoviesPageDto content(List<MovieDto> content) {
        this.content = content;
        return this;
    }

    public MoviesPageDto addContentItem(MovieDto contentItem) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(contentItem);
        return this;
    }

    /**
     * Get content
     *
     * @return content
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<MovieDto> getContent() {
        return content;
    }

    public void setContent(List<MovieDto> content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoviesPageDto moviesPage = (MoviesPageDto) o;
        return Objects.equals(this.pageSize, moviesPage.pageSize) && Objects.equals(this.pageNumber, moviesPage.pageNumber) && Objects.equals(this.elementsNumber, moviesPage.elementsNumber) && Objects.equals(this.totalElements, moviesPage.totalElements) && Objects.equals(this.totalPages, moviesPage.totalPages) && Objects.equals(this.content, moviesPage.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageSize, pageNumber, elementsNumber, totalElements, totalPages, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MoviesPageDto {\n");

        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
        sb.append("    elementsNumber: ").append(toIndentedString(elementsNumber)).append("\n");
        sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

