package org.vladimir.homeArchive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladimir.homeArchive.model.object.MediaType;
import org.vladimir.homeArchive.domain.mediatype.MediaTypeService;
import org.vladimir.homeArchive.model.request.MediaTypeRequest;
import org.vladimir.homeArchive.model.response.MediaTypeResponse;

import java.util.List;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class MediaTypeController {
    private final MediaTypeService mediaTypeService;

    @GetMapping("/mediaTypeById")
    public MediaTypeResponse getMediaTypeById(@RequestParam Long id){
        return mediaTypeService.getObjectResponseById(id);
    }

    @GetMapping("/mediaTypes")
    public List<MediaTypeResponse> getAllMediaTypes(){
        return mediaTypeService.getAllObjectsResponse();
    }

    @PostMapping("/addMediaType")
    public void addMediaType(@RequestBody MediaTypeRequest request){
        if(request.name() == null || request.pathToMedia() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        mediaTypeService.addObject(request);
    }

    @PostMapping("/updateMediaType")
    public void updateMediaType(@RequestBody MediaTypeRequest request){
        if(request.id() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        mediaTypeService.updateObject(request);
    }

    @GetMapping("/deleteMediaType")
    public void deleteMediaType(@RequestParam Long id ){
        mediaTypeService.deleteObject(id);
    }
}
