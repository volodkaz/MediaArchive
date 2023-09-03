package org.vladimir.homeArchive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladimir.homeArchive.domain.mediapropertysettings.MediaPropertySettingService;
import org.vladimir.homeArchive.model.request.MediaPropertySettingRequest;
import org.vladimir.homeArchive.model.response.MediaPropertySettingResponse;

import java.util.List;

@RestController
@RequestMapping("/propertySettings")
@RequiredArgsConstructor
public class MediaPropertySettingsController {
    private final MediaPropertySettingService service;

    @GetMapping("/MediaPropertySettingById")
    public MediaPropertySettingResponse getMediaPropertySettingById(@RequestParam Long id){
        return service.getObjectResponseById(id);
    }

    @GetMapping("/MediaPropertySettings")
    public List<MediaPropertySettingResponse> getAllMediaProperties(){
        return service.getAllObjectsResponse();
    }

    @PostMapping("/addMediaPropertySetting")
    public void addMediaPropertySetting(@RequestBody MediaPropertySettingRequest request){
        if(request.typeId() == null || request.propertyId() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        service.addObject(request);
    }

    @PostMapping("/updateMediaPropertySetting")
    public void updateMediaPropertySetting(@RequestBody MediaPropertySettingRequest request){
        if(request.id() == null){
            throw new IllegalArgumentException("Не указаны обязательные параметры");
        }

        service.updateObject(request);
    }

    @GetMapping("/deleteMediaPropertySetting")
    public void deleteMediaPropertySetting(@RequestParam Long id ){
        service.deleteObject(id);
    }
}
