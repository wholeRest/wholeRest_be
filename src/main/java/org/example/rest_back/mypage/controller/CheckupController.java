package org.example.rest_back.mypage.controller;

import lombok.AllArgsConstructor;
import org.example.rest_back.mypage.dto.CheckupDto;
import org.example.rest_back.mypage.service.CheckupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/checkup")
public class CheckupController {
    private final CheckupService checkupService;

    //get checkup by eventId
    @GetMapping("/{eventId}")
    public ResponseEntity<List<CheckupDto>> getCheckupByEventId(@PathVariable int eventId){
        List<CheckupDto> checkups = checkupService.getCheckupByEventId(eventId);
        return ResponseEntity.ok(checkups);
    }

    //create checkup
    @PostMapping("/{eventId}")
    public ResponseEntity<Void> createCheckup(@PathVariable int eventId, @RequestBody CheckupDto checkupDto){
        checkupService.createCheckup(eventId, checkupDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //update checkup
    @PatchMapping("/{checkupId}")
    public ResponseEntity<Void> updateCheckup(@PathVariable int checkupId, @RequestBody CheckupDto checkupDto){
        checkupService.updateCheckup(checkupId, checkupDto);
        return  ResponseEntity.ok().build();
    }

    //delete checkup
    @DeleteMapping("/{checkupId}")
    public ResponseEntity<Void> deleteCheckup(@PathVariable int checkupId){
        checkupService.deleteCheckup(checkupId);
        return ResponseEntity.ok().build();
    }

}
