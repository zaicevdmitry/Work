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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.util.concurrent.ForkJoinTask.invokeAll;


/**
 * Created with IntelliJ IDEA.
 * User: Dimon
 * Date: 24.03.14
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class Openfile extends RecursiveAction {
    protected  String mainFile;
    protected  String tempFile1;
    protected  String tempFile2;


    public void setMainFileName(final String pMainFileName) {
        mainFile = pMainFileName;
    }

    public void readfile() throws IOException{
        // путь к файлу
        mainFile = "D://test.txt";

        if (mainFile.isEmpty()){
            System.out.print("File is empty!");
            return;
        }

        try {
            FileInputStream file = new FileInputStream(new File(mainFile));
            // количество байт доступных для чтения
            int availableSize = file.available();
            // выводим в консоль количество байт доступных для чтения
            System.out.println("Size:" + availableSize);
            int sizeMass=10485801;
            if (availableSize < sizeMass){
                //  считываем данные из файла и запихиваем их в лист
                ArrayList<Integer> data= new ArrayList<Integer>();

                for(int i=0; i<availableSize; i++){
                    data.add(file.read());
                }
                file.close();
                Mergesort task = new Mergesort();
                task.setData(data);
                task.fork();
                //                получаем отсортированный массив
                List<Integer> sort = (List<Integer>) task.join();
//                открываем главный файл для записи

                FileOutputStream mainResult = new FileOutputStream((new File(mainFile)));

//                записываем в него отсортированный массив
                for (Integer aSort : sort) {
                    mainResult.write(aSort);
                }

                mainResult.close();

//                закрываем текущую задачу
                return;
            }

            //            пути для временных файлов
            tempFile1 = File.createTempFile("bigSort", "tmp").getAbsolutePath();
            tempFile2 = File.createTempFile("bigSort", "tmp").getAbsolutePath();


//            открываем temp для записи
            FileOutputStream tempFileWrite0 = new FileOutputStream((new File(tempFile1)));
            FileOutputStream tempFileWrite1 = new FileOutputStream((new File(tempFile2)));

            while (availableSize > 0) {
                int listSize = availableSize;

//                находим середину
                int middleToRead = listSize / 2;

//                первую половину куска записываем в 0й temp
                for (int i = 0; i < middleToRead; i++) {
                    tempFileWrite0.write(file.read());
                }

//                а вторую половину куска записываем в 1й temp
                for (int i = middleToRead + 1; i <= listSize; i++) {
                    tempFileWrite1.write(file.read());
                }

//                перечитываем количество осавшихся байтов для чтения
                listSize = availableSize = file.available();


            }

//            закрываем все файлы
            file.close();
            tempFileWrite0.close();
            tempFileWrite1.close();

//            создаем подзадачи
            Openfile subTask0 = new Openfile();
            Openfile subTask1 = new Openfile();
            subTask0.setMainFileName(tempFile1);
            subTask1.setMainFileName(tempFile2);

//            запускаем подзадачи и ждем их выполнения
            invokeAll(subTask0, subTask1);

//           объединяем temp файлы
            merge();
        } catch (FileNotFoundException e) {
            System.out.print("File not found!" + e.getMessage());
        }
    }
    protected void merge() throws IOException {


//        файлы
        FileInputStream  temp1File = new FileInputStream(new File(tempFile1));
        FileInputStream  temp2File = new FileInputStream(new File(tempFile2));
        FileOutputStream  resultFile = new FileOutputStream(new File(mainFile));



//        сюда читаем из temp файлов
        Integer from0 = null;
        Integer from1 = null;

//        количество доступных байт для чтения
        int available0 = temp1File.available();
        int available1 = temp2File.available();

//        пока есть что читать в обоих файлах одновременно
        while (available0 > 0 && available1 > 0) {
            if (from0 == null) {
                from0 = temp1File.read();
            }

            if (from1 == null) {
                from1 = temp2File.read();
            }

            if (from0 < from1) {
                resultFile.write(from0);
                from0 = null;
            } else {
                resultFile.write(from1);
                from1 = null;
            }

            available0 = temp1File.available();
            available1 = temp2File.available();
        }

//        после цикла выше в одном из файлов еще что-нить осталось вот это мы и дочитываем
        while (available0 > 0) {
            from0 = temp1File.read();
            resultFile.write(from0);
            available0 = temp1File.available();
        }

        while (available1 > 0) {
            from1 = temp2File.read();
            resultFile.write(from1);
            available1 = temp2File.available();
        }

//        теперь имеем отсортированные данные в исходном файле временные файлы можно удалить
        resultFile.close();
        temp1File.close();
        temp2File.close();
        File f0 = new File(tempFile1);
        f0.delete();
        File f1 = new File(tempFile2);
        f1.delete();
    }


    @Override
    protected void compute() {
        try {
            readfile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

