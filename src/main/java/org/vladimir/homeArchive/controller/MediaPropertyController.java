package org.vladimir.homeArchive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladimir.homeArchive.domain.mediaproperty.MediaPropertyService;
import org.vladimir.homeArchive.model.request.MediaPropertyRequest;
import org.vladimir.homeArchive.model.response.MediaPropertyResponse;

import java.util.List;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class MediaPropertyController {
    private final MediaPropertyService service;

    @GetMapping("/mediaPropertyById")
    public MediaPropertyResponse getMediaPropertyById(@RequestParam Long id){
        return service.getObjectResponseById(id);
    }

    @GetMapping("/mediaProperties")
    public List<MediaPropertyResponse> getAllMediaProperties(){
        return service.getAllObjectsResponse();
    }

    @PostMapping("/addMediaProperty")
    public void addMediaProperty(@RequestBody MediaPropertyRequest request){
        if(request.name() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        service.addObject(request);
    }

    @PostMapping("/updateMediaProperty")
    public void updateMediaProperty(@RequestBody MediaPropertyRequest request){
        if(request.id() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        service.updateObject(request);
    }

    @GetMapping("/deleteMediaProperty")
    public void deleteMediaProperty(@RequestParam Long id ){
        service.deleteObject(id);
    }

}
