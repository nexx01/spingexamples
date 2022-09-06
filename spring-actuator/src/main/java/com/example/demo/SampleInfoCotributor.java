package com.example.demo;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SampleInfoCotributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
//        long tacoCount = tacoRepo.count().block();
//        Map<String, Object> tacoMap = new HashMap<String, Object>();
//        tacoMap.put("count", tacoCount);
//        builder.withDetail("taco-stats", tacoMap);


        long count=1;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("count", count);
        builder.withDetail("taco-sstats", stringObjectHashMap);

    }
}
