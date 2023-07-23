package com.radisys.codeathon.allocator.repositories;

import com.radisys.codeathon.allocator.model.NemsRecord;

import java.util.Map;

public interface INemsRepository {
    Boolean saveNemsRecord(NemsRecord nemsRecord, String negId);
    String getNemsRecord(String key);
    Map getAllNemsRecord();

    void updateNemsRecord(NemsRecord nemsRecord);
}