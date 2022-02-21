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

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("errorDetail")
    private String errorDetail;

    public ProblemDto errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Get errorMessage
     *
     * @return errorMessage
     */
    @ApiModelProperty(example = "BAD REQUEST", value = "")


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ProblemDto errorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
        return this;
    }

    /**
     * Get errorDetail
     *
     * @return errorDetail
     */
    @ApiModelProperty(example = "Movie Not Found", value = "")


    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
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
        return Objects.equals(this.errorMessage, problem.errorMessage) &&
                Objects.equals(this.errorDetail, problem.errorDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, errorDetail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProblemDto {\n");

        sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
        sb.append("    errorDetail: ").append(toIndentedString(errorDetail)).append("\n");
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

