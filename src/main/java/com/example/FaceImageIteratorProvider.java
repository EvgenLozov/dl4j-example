package com.example;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.datasets.iterator.IteratorDataSetIterator;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FaceImageIteratorProvider {

    private NativeImageLoader imageLoader = new NativeImageLoader(100, 100);
    private String faceImagesDir = "/home/yevhen/Downloads/face_detection_data";

    private int batchSize;

    public FaceImageIteratorProvider(int batchSize) {
        this.batchSize = batchSize;
    }

    public DataSetIterator get(){

         List<String> faces = Arrays.stream(new File(faceImagesDir).listFiles()).flatMap(f -> Arrays.stream(f.listFiles()))
                 .filter(f -> f.isFile())
                 .map(f -> f.getAbsolutePath())
                 .collect(Collectors.toList());

        Collections.shuffle(faces);

         List<String> labels = faces.stream()
                 .map(face -> new File(face).getParentFile().getName())
                 .distinct()
                 .collect(Collectors.toList());

         Iterator<DataSet> iterator = new ImageIterator(faces, imageLoader, labels);

         return new IteratorDataSetIterator(iterator, batchSize);
     }
}
