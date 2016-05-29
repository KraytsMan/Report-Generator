package com.softjourn.report.generator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.softjourn.report.generator.model.ReportSettings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.softjourn.report.generator.util.Constants.print;

public class ReportTypeReader {

    public static ReportSettings uploadReportSettings(String path) {
        print("Reading input setting");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        Path file = Paths.get(path);
        try {
            InputStream is = Files.newInputStream(file);
            ReportSettings reportSettings = mapper.readValue(is, ReportSettings.class);
            return reportSettings;
        } catch (IOException e) {
            print(e.getStackTrace().toString());
            return null;
        }
    }

}
