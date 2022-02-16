package recommendersystem.movierecommender.moviecomponent.adapters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import recommendersystem.movierecommender.moviecomponent.entities.Genre;
import recommendersystem.movierecommender.moviecomponent.entities.Movie;

import java.util.ArrayList;
import java.util.List;

@Mapper
interface MovieRecordMapper {

    @Mapping(target = "genresList", expression = "java( jsonRecordsListToGenresList(movieRecord.getGenres()) )")
    @Mapping(target = "productionCompanies", expression = "java( jsonRecordsListToStringList(movieRecord.getProductionCompanies() ) )")
    @Mapping(target = "productionCountries", expression = "java( jsonRecordsListToStringList(movieRecord.getProductionCountries() ) )")
    Movie recordToMovie(MovieRecord movieRecord);

    default List<String> jsonRecordsListToStringList(List<JsonRecord> jsonRecordList) {
        List<String> stringList = new ArrayList<>();
        for (JsonRecord jsonRecord : jsonRecordList) {
            stringList.add(jsonRecord.getName());
        }
        return stringList;
    }

    List<Genre> jsonRecordsListToGenresList(List<JsonRecord> jsonRecordList);

    default Genre jsonRecordToGenre(JsonRecord jsonRecord) {
        if (scienceFictionRecord(jsonRecord)) {
            return Genre.SCIENCE_FICTION;
        }
        try {
            return Genre.valueOf(jsonRecord.getName().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Genre.OTHER;
        }
    }

    private boolean scienceFictionRecord(JsonRecord jsonRecord) {
        String recordName = jsonRecord.getName().toLowerCase();
        return recordName.contains("science") && recordName.contains("fiction");
    }
}
