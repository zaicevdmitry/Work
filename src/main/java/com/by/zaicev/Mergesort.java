package com.by.zaicev;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created with IntelliJ IDEA.
 * User: Dimon
 * Date: 24.03.14
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class Mergesort extends RecursiveTask {
    protected List<Byte> data = new ArrayList<Byte>();

    public List<Byte> sorting(List<Byte> dataSort) {
        int listSize = dataSort.size();

        // если массив меньше единицы, то возвращем его как есть
        if (listSize <= 1) {
            return dataSort;
        } else {
            // Если не то делим на 2
            int middlelist = listSize / 2;

            return mergelist(
                    sorting(dataSort.subList(0, middlelist)),
                    sorting(dataSort.subList(middlelist, listSize))
            );

        }

    }

    public void setData(final List<Byte> pData) {
        data = pData;
    }

    // здесь надо смержить два отсортированных массива
    private List<Byte> mergelist(List<Byte> firstListMerge, List<Byte> secondListMerge) {

        //        обявление массива результирующего массива
        List<Byte> resultList = new ArrayList<Byte>();
        //        дновременно проходим оба входных масиива
        //        Храним текущий элемент каждого массива
        int firstIndexSort = 0;
        int secondIndexSort = 0;

        Byte firstIndex = firstListMerge.get(firstIndexSort);
        Byte secondfIndex = secondListMerge.get(secondIndexSort);

        int mergeSize = firstListMerge.size();


        for (int i = 0; i < mergeSize; i++) {
            //        1 сравниваем эти два элемента между собой
            if (firstIndexSort < firstListMerge.size() && secondIndexSort < secondListMerge.size()) {
                if (firstIndex < secondfIndex) {
                    //меньший из них записываем в результат
                    //Берем следующий элемент массива  вместо того которого мы записали в результат
                    resultList.add(firstListMerge.get(firstIndex++));
                } else {
                    resultList.add(secondListMerge.get(secondfIndex++));
                }
                //            переходим на 1 до тех пор пока в одном из масивов не закончатся элементы
            } else if (firstIndex < firstListMerge.size()) {
                //определяем в каком массиве остались элементы
                //С этого массыва все оставшиеся элементы записываем в результат
                resultList.add(firstListMerge.get(firstIndex++));
            } else if (secondfIndex < secondListMerge.size()) {
                resultList.add(secondListMerge.get(secondfIndex++));
            }
        }
//        int indexmass= 0;
//        while(indexmass < mergeSize){
//            indexmass++;
//            if (firstIndexSort < firstListMerge.size() && secondIndexSort < secondListMerge.size()) {
//                if (firstIndex < secondfIndex) {
//                    //меньший из них записываем в результат
//                    //Берем следующий элемент массива  вместо того которого мы записали в результат
//                    resultList.add(firstListMerge.get(firstIndex++));
//                } else {
//                    resultList.add(secondListMerge.get(secondfIndex++));
//                }
//            }
//            //        вернуть результат
        return resultList;
    }


    protected Object compute() {

        int hresholdMemory = 4096;
        int dataSize = data.size();

        //        если мы достигли порогового значения
        if (dataSize < hresholdMemory) {
            //            то выполняем сортировку своей части
            return sorting(data);
        } else {
            //            в противном случае продолжаем разбивать
            Mergesort subTask0 = new Mergesort();
            Mergesort subTask1 = new Mergesort();

            int middleIndex = dataSize / 2;

            subTask0.setData(data.subList(0, middleIndex));
            subTask1.setData(data.subList(middleIndex, dataSize));

            //            запускаем подзадачи
            subTask0.fork();
            subTask1.fork();

            //            получаем результат подзадач
            List<Byte> result0 = (List<Byte>) subTask0.join();
            List<Byte> result1 = (List<Byte>) subTask1.join();

            //            объединяем с сортировкой результат подзадач и возвращаем его головной задаче
            return mergelist(result0, result1);
        }
    }
}





