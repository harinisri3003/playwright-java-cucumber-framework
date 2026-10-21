package com.qa.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataManager {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, String> getTestData(
            String sheetName, String testCaseKey) {

        String filePath = "testdata/json/login-data.json";

        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "JSON test data file not found: " + filePath);
            }

            Map<String, List<Map<String, String>>> allData =
                    mapper.readValue(inputStream,
                            new TypeReference<Map<String, List<Map<String, String>>>>() {});

            List<Map<String, String>> rows = allData.get(sheetName);

            if (rows == null) {
                throw new RuntimeException(
                        "JSON data section not found: " + sheetName);
            }

            for (Map<String, String> row : rows) {
                String scenarioName = row.get("ScenarioName");

                if (scenarioName != null
                        && scenarioName.equalsIgnoreCase(testCaseKey)) {
                    return row;
                }
            }

            throw new RuntimeException(
                    "JSON Data Error: No row found for key '" + testCaseKey + "'");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load JSON test data: " + e.getMessage(), e);
        }
    }
}