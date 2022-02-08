package recommendersystem.movierecommender.controllers;

import recommendersystem.movierecommender.mappers.HomeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
@Slf4j
public class HomeController {

    private HomeMapper homeMapper;

    @Autowired
    public HomeController(HomeMapper homeMapper) {
        this.homeMapper = homeMapper;
    }

    @PostMapping
    ResponseEntity<TemplateResponse> createTemplate(@RequestBody TemplateRequest request) {
        return ResponseEntity.ok(homeMapper.requestToResponse(request));
    }
}
