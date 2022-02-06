package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.distance.EuclideanDistance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException {
        //TODO implement csv to records function
        ArrayList<List<String>> recordlist = new ArrayList<>();
//        List<Record> records =  new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/java/org/example/files/sample.csv"));) {
            String[] values = null;

            while ((values = csvReader.readNext()) != null) {
                recordlist.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        List<Record> records = new ArrayList<>();
        List<String> des = recordlist.get(0);
        recordlist.remove(0);
        for (List<String> strings : recordlist) {
            Map<String,Double> test = new HashMap<>();
            for (int j = 0; j < strings.size(); j++) {
                test.put(des.get(j).toString(),Double.parseDouble(strings.get(j)));

            }
            records.add(new Record(des.toString(),test));
        }
        Map<Centroid, List<Record>> clusters = KMeans.fit(records, 2, new EuclideanDistance(), 10000);
        // Printing the cluster configuration
        clusters.forEach((key, value) -> {
            System.out.println("-------------------------- CLUSTER ----------------------------");
            System.out.println(key);
            value.forEach(record -> System.out.println(record.getFeatures()));
            System.out.println();
            System.out.println();

        });

        //TODO implement function to create clustered file

    }
}