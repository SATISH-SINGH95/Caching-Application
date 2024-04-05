package com.chaching.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileUtils {   // These two methods will be used to compress and decompress the file before saving into the db.

    public static byte[] compressFile(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while(!deflater.finished()){
            int size = deflater.deflate(tmp);
            byteArrayOutputStream.write(tmp, 0, size);
        }

        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {
            // throw required exception
        }

        return byteArrayOutputStream.toByteArray();
    }


    public static byte[] decompressFile(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while(!inflater.finished()){
                int count = inflater.inflate(tmp);
                byteArrayOutputStream.write(tmp, 0, count);
            }
            byteArrayOutputStream.close();
        } catch (Exception e) {
            // throw required exception
        }

        return byteArrayOutputStream.toByteArray();
    }

}
