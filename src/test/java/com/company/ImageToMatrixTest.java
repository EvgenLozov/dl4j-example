package com.company;

import org.datavec.image.loader.BaseImageLoader;
import org.datavec.image.loader.ImageLoader;
import org.datavec.image.loader.NativeImageLoader;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.nativeblas.Nd4jBlas;

import java.io.File;
import java.io.IOException;

public class ImageToMatrixTest {

    @Test
    public void test() throws IOException {
        Nd4jBlas nd4jBlas = (Nd4jBlas) Nd4j.factory().blas();

        BaseImageLoader imageLoader = new NativeImageLoader(100, 100);
        INDArray res = imageLoader.asMatrix(new File("/home/yevhen/Downloads/face_detection_data/1075237/face_8967453.jpg"));

        res.sub(128).mul(1.0/128);
    }
}
