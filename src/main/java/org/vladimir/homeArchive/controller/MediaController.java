package org.vladimir.homeArchive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladimir.homeArchive.domain.mediadata.MediaService;
import org.vladimir.homeArchive.model.request.MediaDataRequest;
import org.vladimir.homeArchive.model.response.MediaDataResponse;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService service;

    @GetMapping("/mediaById")
    public MediaDataResponse getMediaById(@RequestParam Long id){
        return service.getObjectResponseById(id);
    }

    @GetMapping("/medias")
    public List<MediaDataResponse> getAllMedia(){
        return service.getAllObjectsResponse();
    }

    @PostMapping("/addMedia")
    public void addMedia(@RequestBody MediaDataRequest request){
//        if(request.name() == null){
//            throw new IllegalArgumentException("Не указаны обязательные параметры");
//        }

        service.addObject(request);
    }

    @PostMapping("/updateMedia")
    public void updateMedia(@RequestBody MediaDataRequest request){
//        if(request.id() == null){
//            throw new IllegalArgumentException("Не указаны обязательные параметры");
//        }

        service.updateObject(request);
    }

    @GetMapping("/deleteMedia")
    public void deleteMedia(@RequestParam Long id ){
        service.deleteObject(id);
    }

}
