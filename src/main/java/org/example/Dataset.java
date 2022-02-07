package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dataset {
    private List<String> header;
    private List<Record> records = new ArrayList<>();

    public Dataset(String filepath) {
        ArrayList<List<String>> rows = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] values;

            while ((values = csvReader.readNext()) != null) {
                rows.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        this.header = rows.get(0);
        rows.remove(0);
        for (List<String> row : rows) {
            Map<String, Double> features = new HashMap<>();
            for (int j = 0; j < row.size(); j++) {
                features.put(this.header.get(j), Double.parseDouble(row.get(j)));

            }
            this.records.add(new Record(features));
        }
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dataset)) return false;
        Dataset dataset = (Dataset) o;
        return Objects.equals(getHeader(), dataset.getHeader()) && Objects.equals(getRecords(), dataset.getRecords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeader(), getRecords());
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "header=" + header +
                ", records=" + records +
                '}';
    }
}
