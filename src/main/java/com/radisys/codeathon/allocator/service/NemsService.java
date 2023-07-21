package com.radisys.codeathon.allocator.service;

import com.radisys.codeathon.allocator.model.NemsRecord;
import com.radisys.codeathon.allocator.repositories.INemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class NemsService implements INemsService {

    final Logger logger = LoggerFactory.getLogger(NemsService.class);

    INemsRepository nemsRepository;

    public NemsService(INemsRepository nemsRepository) {
        this.nemsRepository = nemsRepository;
    }

    @Override
    public void saveNemsRecord(NemsRecord nemsRecord, String hashKey) {
        nemsRepository.saveNemsRecord(nemsRecord, hashKey);
    }

    @Override
    public String getNemsRecord(String negId) {
        logger.info("[NemsService] Inside Nems Service with negId as {} ", negId);
        return nemsRepository.getNemsRecord(negId);
    }

    @Override
    public void updateNemsRecord(NemsRecord nemsRecord) {
        nemsRepository.updateNemsRecord(nemsRecord);
    }

    @Override
    public Map getAllNemsRecord() {
        return nemsRepository.getAllNemsRecord();
    }


    @Override
    public void updateNemsRecordInBulk(ArrayList<NemsRecord> nemsRecordList) {
        nemsRepository.updateNemsRecordInBulk(nemsRecordList);
    }
}