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
    private final String hashReference= "NEMS_NEG_MAPPING";
    INemsService nemsService;
    @Autowired
    PCLObservable pclObservable;
    @Autowired
    PCLObserver pclObserver;
    public static String status = null;

    public NemsRecordController(INemsService nemsService) {
        this.nemsService = nemsService;
    }

    public static String desiredAllocation = null;
    public static String currentAllocation = null;

    //The function receives a POST request, and create a NEMS Record with NEG ID as key
    @PostMapping
    public ResponseEntity<String> createNemsRecord(@RequestBody NemsRecord nemsRecord) {
        nemsService.saveNemsRecord(nemsRecord, nemsRecord.getNegId());
        return new ResponseEntity<>(nemsService.getNemsRecord(nemsRecord.getNegId()), HttpStatus.OK);
    }

    //The function receives a POST request, and create a List of NEMS Record with NEG ID as key
    @PostMapping({"/createNemsRecordInBulk"})
    public ResponseEntity<ArrayList<NemsRecord>> createNemsRecordInBulk(@RequestBody ArrayList<NemsRecord> nemsRecordList) {
        nemsRecordList.stream().forEach(nemsRecord -> {
            nemsService.saveNemsRecord(nemsRecord, nemsRecord.getNegId());
        });

        return new ResponseEntity<>(nemsRecordList, HttpStatus.OK);
    }

    // The function receives a PUT request, updates the NemsRecord based on Status
    // Status represents health of the particular NEMS
    @PutMapping({"/{negId}"})
    public ResponseEntity<String> updateNemsRecord(@PathVariable String negId,
                                                   @RequestBody NemsRecord nemsRecord) {

        pclObservable.addPropertyChangeListener(pclObserver);
        NemsRecord observedNemsRecord = pclObservable.observeNemsRecord(nemsRecord);
        nemsService.updateNemsRecord(observedNemsRecord);
        return new ResponseEntity<>(nemsService.getNemsRecord(negId), HttpStatus.OK);
    }

    //The function receives a PUT request in bulk, updates the NemsRecord
    @PutMapping({"/updateNemsRecordInBulk"})
    public ResponseEntity<ArrayList<NemsRecord>> updateNemsRecordInBulk(@RequestBody ArrayList<NemsRecord> nemsRecordList) {
        nemsService.updateNemsRecordInBulk(nemsRecordList);
        return new ResponseEntity<>(nemsRecordList, HttpStatus.OK);
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