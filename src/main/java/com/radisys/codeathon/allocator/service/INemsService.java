package com.radisys.codeathon.allocator.service;

import com.radisys.codeathon.allocator.model.NemsRecord;

import java.util.ArrayList;
import java.util.Map;

public interface INemsService {

    Boolean saveNemsRecord(NemsRecord nemsRecord, String hashKey);

    String getNemsRecord(String neg_key);

    void updateNemsRecord(NemsRecord nemsRecord);

    Map getAllNemsRecord();

}