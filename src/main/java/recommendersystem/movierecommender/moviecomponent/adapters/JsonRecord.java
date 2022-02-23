package recommendersystem.movierecommender.moviecomponent.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
class JsonRecord implements Serializable {
    private String id;
    private String name;
    @JsonProperty("iso_3166_1")
    private String ignoredField1;
}
