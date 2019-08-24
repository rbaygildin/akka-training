package org.rbaygildin.akkatraining.web.controller;

import org.rbaygildin.akkatraining.service.ParsingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("parsing")
public class ParsingApiController {

    private final ParsingService service;

    public ParsingApiController(ParsingService service) {
        this.service = service;
    }

    @PostMapping
    public void startParsing(@RequestParam String url){
        service.startParsing(url);
    }

    @GetMapping
    public Map<String, List<String>> getKeywords(){
        return service.getKeywords();
    }
}
