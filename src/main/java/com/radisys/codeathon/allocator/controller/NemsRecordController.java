package com.radisys.codeathon.allocator.controller;

import com.radisys.codeathon.allocator.model.NemsRecord;
import com.radisys.codeathon.allocator.observable.PCLObservable;
import com.radisys.codeathon.allocator.observer.PCLObserver;
import com.radisys.codeathon.allocator.service.INemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/nems")
public class NemsRecordController {

    final Logger logger = LoggerFactory.getLogger(NemsRecordController.class);

    INemsService nemsService;
    @Autowired
    PCLObservable pclObservable;
    @Autowired
    PCLObserver pclObserver;

    public NemsRecordController(INemsService nemsService) {
        this.nemsService = nemsService;
    }


    //The function receives a POST request, and create a List of NEMS Record with NEG ID as key
    @PostMapping
    public ResponseEntity<ArrayList<NemsRecord>> createNemsRecords(@RequestBody ArrayList<NemsRecord> nemsRecordList) {
        nemsRecordList.forEach(nemsRecord -> {
            nemsService.saveNemsRecord(nemsRecord, nemsRecord.getNegId());
        });
        return new ResponseEntity<>(nemsRecordList, HttpStatus.OK);
    }

    //The function receives a PUT request in bulk, updates the NemsRecord
    @PutMapping
    public ResponseEntity<ArrayList<NemsRecord>> updateNemsRecords(@RequestBody ArrayList<NemsRecord> nemsRecordList) {
        nemsRecordList.forEach(nemsRecord -> {
            pclObservable.addPropertyChangeListener(pclObserver);
            NemsRecord observedNemsRecord = pclObservable.observeNemsRecord(nemsRecord);
            nemsService.updateNemsRecord(observedNemsRecord);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a NemsRecord as a response.
    @GetMapping({"/{negId}"})
    public ResponseEntity<String> getNemsRecord(@PathVariable String negId) {
        logger.info("Fetching Records for hashKey {}", negId);
        return new ResponseEntity<>(nemsService.getNemsRecord(negId), HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a NemsRecord as a response.
    @GetMapping
    public ResponseEntity<Map> getAllNemsRecord() {
        logger.info("Fetching all Records..K, HK and HV..");
        return new ResponseEntity<>(nemsService.getAllNemsRecord(), HttpStatus.OK);
    }

}