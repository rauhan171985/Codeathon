package com.radisys.codeathon.allocator.repositories;

import com.radisys.codeathon.allocator.model.NemsRecord;

import java.util.ArrayList;
import java.util.Optional;

public interface INemsRepository {
    void saveNemsRecord(NemsRecord nemsRecord, String negId);
    String getNemsRecord(String key);
    void updateNemsRecord(NemsRecord nemsRecord);
    void updateNemsRecordInBulk(ArrayList<NemsRecord> nemsRecordList);
}