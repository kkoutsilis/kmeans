package org.example;

import org.example.distance.EuclideanDistance;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        Dataset dataset = new Dataset("src/main/java/org/example/files/sample.csv");

        Map<Centroid, List<Record>> clusters = KMeans.fit(dataset.getRecords(), 2, new EuclideanDistance(), 10000);

        clusters.forEach((key, value) -> {
            System.out.println("-------------------------- CLUSTER ----------------------------");
            System.out.println(key);
            value.forEach(record -> System.out.println(record.getFeatures()));
            System.out.println();
            System.out.println();

        });
        System.out.println("Number of clusters: " + clusters.size());
        //TODO implement function to create clustered file

    }
}