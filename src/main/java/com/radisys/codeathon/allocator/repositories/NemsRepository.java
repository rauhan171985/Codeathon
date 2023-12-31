package com.radisys.codeathon.allocator.repositories;

import com.google.gson.Gson;
import com.radisys.codeathon.allocator.model.NemsRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NemsRepository implements INemsRepository {

    final Logger logger = LoggerFactory.getLogger(NemsRepository.class);

    private final String key = "NEMS_NEG_MAPPING";
    private HashOperations hashOperations;

    public NemsRepository(@Autowired RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Boolean saveNemsRecord(NemsRecord nemsRecord, String hashKey) {
        Gson gson = new Gson();
        return hashOperations.putIfAbsent(key, hashKey, gson.toJson(nemsRecord));
    }

    @Override
    public String getNemsRecord(String negId) {
        logger.info("[NemsRepository] Inside Nems REPO with negId as {} ", negId);
        return (String) hashOperations.get(key, negId);
    }

    @Override
    public Map getAllNemsRecord() {
        logger.info("[NemsRepository] Fetching all records with key {} ", key);
        return hashOperations.entries(key);
    }

    @Override
    public void updateNemsRecord(NemsRecord nemsRecord) {
        Gson gson = new Gson();
        hashOperations.put(key, nemsRecord.getNegId(), gson.toJson(nemsRecord));
        logger.info(String.format("NEMS with NEG ID %s updated", nemsRecord.getNegId()));
    }

}