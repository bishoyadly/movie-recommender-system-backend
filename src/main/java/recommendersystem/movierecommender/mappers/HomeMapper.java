package recommendersystem.movierecommender.mappers;

import recommendersystem.movierecommender.controllers.TemplateRequest;
import recommendersystem.movierecommender.controllers.TemplateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeMapper {
    TemplateResponse requestToResponse(TemplateRequest request);

    TemplateRequest responseToRequest(TemplateResponse response);
}
