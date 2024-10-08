package org.example.rest_back.mypage.controller;

import lombok.AllArgsConstructor;
import org.example.rest_back.mypage.dto.CautionDto;
import org.example.rest_back.mypage.service.CautionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/caution")
public class CautionController {
    private final CautionService cautionService;

    //get caution by eventId
    @GetMapping("/{eventId}")
    public ResponseEntity<List<CautionDto>> getCautionByEventId(@PathVariable int eventId){
        List<CautionDto> cautions = cautionService.getCautionByEventId(eventId);
        return ResponseEntity.ok(cautions);
    }

    //create caution
    @PostMapping("/{eventId}")
    public ResponseEntity<Void> createCaution(@PathVariable int eventId, @RequestBody CautionDto cautionDto){
        cautionService.createCaution(eventId, cautionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //update caution
    @PatchMapping("/{cautionId}")
    public ResponseEntity<Void> updateCaution(@PathVariable int cautionId, @RequestBody CautionDto cautionDto){
        cautionService.updateCaution(cautionId, cautionDto);
        return  ResponseEntity.ok().build();
    }

    //delete caution
    @DeleteMapping("/{cautionId}")
    public ResponseEntity<Void> deleteCaution(@PathVariable int cautionId){
        cautionService.deleteCaution(cautionId);
        return ResponseEntity.ok().build();
    }
}
