package com.by.zaicev;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: Dimon
 * Date: 24.03.14
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class Openfile {

    public static int[] chars;

    public static void read() throws IOException{
        String filename = "D:/10mb.bin";

        try {
            File file = new File("D:/10mb.bin");
            long length = file.length();
            chars = new int [(int)length];
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int  line;
            while ((line = br.read()) != -1) {
                chars[line] = br.read();


            }
            br.close();


        } catch (IOException e) {
            System.out.print("Файл не найден!");
            e.printStackTrace();
        }

    }
}

