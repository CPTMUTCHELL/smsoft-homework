package com.smartsoft.main.service;

import com.smartsoft.main.dto.RecordDTO;
import com.smartsoft.main.model.Record;
import com.smartsoft.main.repository.RecordRepository;
import com.smartsoft.main.view.SSOIDAndFormIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DBService {

    private final RecordRepository recordRepository;
    @Autowired
    public DBService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;

    }
    @Transactional
    public void addAll(List<Record> records) {
        recordRepository.saveAll(records);
    }

    public void deleteAll() {
        recordRepository.deleteAll();
    }

    public List<RecordDTO> unfinishedForms() {
        return recordRepository.findAllDTOBySubtypeIsNotIn(new String[]{"done", "send", "success"});
    }

    public HashMap<String, Integer> topForms() {
        var hashMap = new HashMap<String, Integer>();
        recordRepository
                .findAll()
                .stream()
                .filter(r -> r.getFormid() != null && !r.getFormid().isEmpty())
                .forEach(record -> hashMap.put(record.getFormid(), hashMap.getOrDefault(record.getFormid(), 0) + 1));

        return hashMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public ArrayList<SSOIDAndFormIDs> formsByTime(Date fromDate, Date toDate) {
        var hashMap = new HashMap<String, ArrayList<String>>();
        recordRepository
                .findAllDTOByYmdhBetween(fromDate, toDate)
                .stream()
                .filter(record -> record.formid() != null && !record.formid().isEmpty())
                .forEach(record -> {
                    if (hashMap.containsKey(record.ssoid())) {
                        hashMap.get(record.ssoid()).add(record.formid());
                    } else {
                        hashMap.put(record.ssoid(), new ArrayList<String>() {{
                            add(record.ssoid());
                        }});
                    }
                });

        return hashMap
                .entrySet()
                .stream()
                .map(record -> new SSOIDAndFormIDs(record.getKey(), record.getValue()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(hashMap.size())));
    }
    public void loadFilesToDbSubsequently(List<Record> records) throws InterruptedException {
        recordRepository.deleteAll();
        for (int i = 0; i < records.size(); i++) {
            recordRepository.save(records.get(i));
            if (i % 1000 == 0){
                Thread.sleep(1000);
            }
        }
    }
}

