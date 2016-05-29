package com.softjourn.report.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softjourn.report.generator.model.Field;
import com.softjourn.report.generator.model.ReportSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.softjourn.report.generator.util.Constants.print;
import static com.softjourn.report.generator.util.Constants.usedIds;
import static java.lang.Math.abs;

public class ReportGenerator {

    private WordsGenerator generator;

    public ReportGenerator() {
        generator = new WordsGenerator("https://www.vocabulary.com/challenge/preview.json");
    }

    public List<String> generate(ReportSettings settings, Long total) throws IOException {
        print("Generating reports using https://www.vocabulary.com/challenge/preview.json");
        List<String> reports = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Map<String, Object> map = new HashMap<>();
            for (Field field : settings.getFields()) {
                if (field.getName().equals("userId")) {
                    map.put(field.getName(), usedIds.get(ThreadLocalRandom.current().nextInt(usedIds.size())));
                    continue;
                }
                if (field.getName().equals("dcid")) {
                    map.put("dcid", settings.getName());
                    continue;
                }
                if (field.getName().equals("documentId")) {
                    map.put(field.getName(), String.valueOf(abs(ThreadLocalRandom.current().nextInt())));
                    continue;
                }
                if (field.getName().equals("_timestamp"))
                    continue;
                else map.put(field.getName(), generator.generateWord());
            }
            reports.add(new ObjectMapper().writeValueAsString(map));
        }
        return reports;
    }

}
