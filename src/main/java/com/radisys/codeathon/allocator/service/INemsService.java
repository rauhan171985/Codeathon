package com.radisys.codeathon.allocator.service;

import com.radisys.codeathon.allocator.model.NemsRecord;

import java.util.ArrayList;
import java.util.Optional;

public interface INemsService {

    void saveNemsRecord(NemsRecord nemsRecord, String hashKey);

    String getNemsRecord(String neg_key);

    void updateNemsRecord(NemsRecord nemsRecord);


    void updateNemsRecordInBulk(ArrayList<NemsRecord> nemsRecordList);
}