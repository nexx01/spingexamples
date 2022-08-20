package com.example.demo;


import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamUtilsSpringTest {
    @Test
    void whenCopyInputStreamToOutputStream_thenCorrect() throws IOException {
        String inputFileName = "src/test/resources/input1.txt";
        String outputFileName = "src/test/resources/output1.txt";

        File outputFile = new File(outputFileName);

        FileInputStream in = new FileInputStream(inputFileName);
        FileOutputStream out = new FileOutputStream(outputFile);

        StreamUtils.copy(in, out);

        assertTrue(outputFile.exists());
       // String inputFileContent = getStringFromInputStream(new FileInputStream(inputFileName));
        //String outputFileContent = getStringFromInputStream(new FileInputStream(outputFileName));
        //assertEquals(inputFileContent, outputFileContent);
    }

    @Test
    void whenCopyRangeOfInputStreamToOutputStream_thenCorrect() throws IOException {
        String inputFileName = "src/test/resources/input2.txt";
        String outFileName = "src/test/resources/output2.txt";

        File outputFile = new File(outFileName);

        FileInputStream in = new FileInputStream(inputFileName);
        FileOutputStream out = new FileOutputStream(outputFile);

        StreamUtils.copyRange(in, out, 1, 10);

        assertTrue(outputFile.exists());
        String inputFileContent = "2222222222";
        String outputFileContent = getStringFromInputStream(new FileInputStream(outputFile));
        assertEquals(inputFileContent, outputFileContent);
    }

    @Test
    void whenCopyInputStreamToString_thenCorrect() throws IOException {
        String inputFileName = "src/test/resources/input3.txt";

        FileInputStream in = new FileInputStream(inputFileName);
        String content = StreamUtils.copyToString(in, StandardCharsets.UTF_8);

        String inputFileContent = getStringFromInputStream(new FileInputStream(inputFileName));

        assertEquals(inputFileContent,content);
    }

    @Test
    void whenCopyByteArrayToOutputStream_thenCorrect4() throws IOException {
        String outputFileName = "src/test/resources/output4.txt";
        String string = "Should be copied to OutPutStream";
        byte[] bytes = string.getBytes();

        FileOutputStream out = new FileOutputStream("src/test/resources/output4.txt");

        StreamUtils.copy(bytes, out);

        String outputFileContent = getStringFromInputStream(new FileInputStream(outputFileName));

        assertEquals(outputFileContent,string);
    }

    public static String getStringFromInputStream(InputStream input) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(input, writer, "UTF-8");
        return writer.toString();
    }
}
