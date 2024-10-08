package org.example.rest_back.mypage.controller;

import lombok.AllArgsConstructor;
import org.example.rest_back.mypage.dto.MedicineDto;
import org.example.rest_back.mypage.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    //get medicine by eventId
    @GetMapping("/{eventId}")
    public ResponseEntity<List<MedicineDto>> getMedicineByEventId(@PathVariable int eventId){
        List<MedicineDto> medicines = medicineService.getMedicineByEventId(eventId);
        return ResponseEntity.ok(medicines);
    }

    //create medicine
    @PostMapping("/{eventId}")
    public ResponseEntity<Void> createMedicine(@PathVariable int eventId, @RequestBody MedicineDto medicineDto){
        medicineService.createMedicine(eventId, medicineDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //update medicine
    @PatchMapping("/{medicineId}")
    public ResponseEntity<Void> updateMedicine(@PathVariable int medicineId, @RequestBody MedicineDto medicineDto){
        medicineService.updateMedicine(medicineId, medicineDto);
        return  ResponseEntity.ok().build();
    }

    //delete medicine
    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int medicineId){
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok().build();
    }
}
