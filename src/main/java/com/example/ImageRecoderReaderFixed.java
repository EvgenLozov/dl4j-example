package com.example;

import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.io.labels.PathLabelGenerator;
import org.datavec.api.split.InputSplit;
import org.datavec.image.recordreader.ImageRecordReader;

import java.io.IOException;
import java.util.ArrayList;

public class ImageRecoderReaderFixed extends ImageRecordReader {

    public ImageRecoderReaderFixed(int height, int width, int channels, PathLabelGenerator labelGenerator) {
        super(height, width, channels, labelGenerator);
    }

    @Override
    public void initialize(InputSplit split) throws IOException {
        super.labels = new ArrayList<>(labels);
        super.initialize(split);
    }
}
