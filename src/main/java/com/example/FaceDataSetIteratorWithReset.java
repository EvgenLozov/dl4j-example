package com.example;

import org.deeplearning4j.datasets.iterator.IteratorDataSetIterator;
import org.nd4j.linalg.dataset.DataSet;

import java.util.Iterator;

public class FaceDataSetIteratorWithReset extends IteratorDataSetIterator {

    private ImageIterator iterator;

    public FaceDataSetIteratorWithReset(ImageIterator iterator, int batchSize) {
        super(iterator, batchSize);
        this.iterator = iterator;
    }

    @Override
    public boolean resetSupported() {
        return true;
    }

    @Override
    public void reset() {
        iterator.reset();
    }
}
