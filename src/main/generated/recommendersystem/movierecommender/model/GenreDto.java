package recommendersystem.movierecommender.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Genre
 */
public enum GenreDto {

    ACTION("ACTION"),

    ANIMATION("ANIMATION"),

    ADVENTURE("ADVENTURE"),

    COMEDY("COMEDY"),

    CRIME("CRIME"),

    DRAMA("DRAMA"),

    EXPERIMENTAL("EXPERIMENTAL"),

    FANTASY("FANTASY"),

    HISTORICAL("HISTORICAL"),

    HISTORY("HISTORY"),

    HORROR("HORROR"),

    ROMANCE("ROMANCE"),

    SCIENCE_FICTION("SCIENCE_FICTION"),

    THRILLER("THRILLER"),

    WESTERN("WESTERN"),

    WAR("WAR"),

    DOCUMENTARY("DOCUMENTARY"),

    MUSIC("MUSIC"),

    MYSTERY("MYSTERY"),

    FAMILY("FAMILY"),

    OTHER("OTHER");

    private String value;

    GenreDto(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static GenreDto fromValue(String value) {
        for (GenreDto b : GenreDto.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

