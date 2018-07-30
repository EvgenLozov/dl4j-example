package com.example;

import org.datavec.image.loader.BaseImageLoader;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ImageIterator implements Iterator<DataSet> {

    private final List<String> images;
    private final BaseImageLoader imageLoader;
    private final List<String> labels;

    private int cursor = 0;

    public ImageIterator(List<String> images, BaseImageLoader imageLoader, List<String> labels) {
        this.images = images;
        this.imageLoader = imageLoader;
        this.labels = labels;
    }

    @Override
    public boolean hasNext() {
        return cursor<images.size();
    }

    @Override
    public DataSet next() {
        String image = images.get(cursor);
        try {
            File fileImage = new File(image);
            INDArray featureMatrix = imageLoader.asMatrix(fileImage).sub(128).mul(1.0/128);

            String label = fileImage.getParentFile().getName();
            int index = label.indexOf(label);

            INDArray labelArray = Nd4j.zeros(labels.size());
            labelArray.putScalar(index, 1);

            cursor++;

            return new DataSet(featureMatrix, labelArray);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
