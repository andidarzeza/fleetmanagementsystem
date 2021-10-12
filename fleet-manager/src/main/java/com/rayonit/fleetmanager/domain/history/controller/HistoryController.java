package com.rayonit.fleetmanager.domain.history.controller;

import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/histories")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllHistories() {
        return historyService.getAllHistories();
    }

    @DeleteMapping("/delete/{historyID}")
    public ResponseEntity<Object> deleteHistory(@PathVariable String historyID){
        return historyService.deleteHistory(historyID);
    }

    @GetMapping("/all/{userID:.+}")
    public ResponseEntity<Object> getAllByUserID(@PathVariable String userID){
        return historyService.getAllByUserID(userID);
    }

}

