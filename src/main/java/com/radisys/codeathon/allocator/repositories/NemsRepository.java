package com.radisys.codeathon.allocator.repositories;

import com.google.gson.Gson;
import com.radisys.codeathon.allocator.model.NemsRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class NemsRepository implements INemsRepository {

    final Logger logger = LoggerFactory.getLogger(NemsRepository.class);

    private final String hashKey = "NEMS_NEG_MAPPING";
    private HashOperations hashOperations;

    public NemsRepository(@Autowired RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveNemsRecord(NemsRecord nemsRecord, String key) {
        Gson gson = new Gson();
        hashOperations.putIfAbsent(hashKey, nemsRecord.getNegId(), gson.toJson(nemsRecord));
        logger.info(String.format("NEMS with NEG ID %s saved", nemsRecord.getNegId()));
    }

    @Override
    public String getNemsRecord(String negId) {
        logger.info("[NemsRepository] Inside Nems REPO with negId as {} ", negId);
        return (String) hashOperations.get(hashKey, negId);
    }

    @Override
    public void updateNemsRecord(NemsRecord nemsRecord) {
        Gson gson = new Gson();
        hashOperations.put(hashKey, nemsRecord.getNegId(), gson.toJson(nemsRecord));
        logger.info(String.format("NEMS with NEG ID %s updated", nemsRecord.getNegId()));
    }

    @Override
    public void updateNemsRecordInBulk(ArrayList<NemsRecord> nemsRecordList) {
        Gson gson = new Gson();
        nemsRecordList.stream().forEach(nemsRecord -> {
            updateNemsRecord(nemsRecord);
            logger.info(String.format("NEMS with NEG ID %s updated", nemsRecord.getNegId()));
        });
    }
}