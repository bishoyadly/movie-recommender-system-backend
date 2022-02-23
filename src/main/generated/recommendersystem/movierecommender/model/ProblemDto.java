package recommendersystem.movierecommender.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * ProblemDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProblemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("errorMessageTitle")
    private String errorMessageTitle;

    @JsonProperty("errorMessageDetail")
    private String errorMessageDetail;

    public ProblemDto errorMessageTitle(String errorMessageTitle) {
        this.errorMessageTitle = errorMessageTitle;
        return this;
    }

    /**
     * Get errorMessageTitle
     *
     * @return errorMessageTitle
     */
    @ApiModelProperty(example = "BAD REQUEST", value = "")


    public String getErrorMessageTitle() {
        return errorMessageTitle;
    }

    public void setErrorMessageTitle(String errorMessageTitle) {
        this.errorMessageTitle = errorMessageTitle;
    }

    public ProblemDto errorMessageDetail(String errorMessageDetail) {
        this.errorMessageDetail = errorMessageDetail;
        return this;
    }

    /**
     * Get errorMessageDetail
     *
     * @return errorMessageDetail
     */
    @ApiModelProperty(example = "Movie Not Found", value = "")


    public String getErrorMessageDetail() {
        return errorMessageDetail;
    }

    public void setErrorMessageDetail(String errorMessageDetail) {
        this.errorMessageDetail = errorMessageDetail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProblemDto problem = (ProblemDto) o;
        return Objects.equals(this.errorMessageTitle, problem.errorMessageTitle) &&
                Objects.equals(this.errorMessageDetail, problem.errorMessageDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessageTitle, errorMessageDetail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProblemDto {\n");

        sb.append("    errorMessageTitle: ").append(toIndentedString(errorMessageTitle)).append("\n");
        sb.append("    errorMessageDetail: ").append(toIndentedString(errorMessageDetail)).append("\n");
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

