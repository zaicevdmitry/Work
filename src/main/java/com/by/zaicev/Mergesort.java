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
    protected List<Integer> data = new ArrayList<Integer>();

    public List<Integer> sorting(List<Integer> dataSort) {
        List<Integer> result;

        int listSize = dataSort.size();

        // если массив меньше единицы, то возвращем его как есть
        if (listSize <= 1) {
            result = dataSort;
        } else {
            // Если не то делим на 2
            int middlelist = listSize / 2;

            List<Integer> firstSublist = dataSort.subList(0, middlelist);
            List<Integer> secondSublist = dataSort.subList(middlelist, listSize);

            List<Integer> firstSortedSublist = sorting(firstSublist);
            List<Integer> secondSortedSublist = sorting(secondSublist);

            result = mergelist(firstSortedSublist, secondSortedSublist);
        }

        return result;
    }

    public void setData(final List<Integer> pData) {
        data = pData;
    }

    // здесь надо смержить два отсортированных массива
    private List<Integer> mergelist(List<Integer> firstListMerge, List<Integer> secondListMerge) {

        //        обявление массива результирующего массива

        //        дновременно проходим оба входных масиива
        //        Храним текущий элемент каждого массива
        int firstArrayIndex = 0;
        int secondArrayIndex = 0;

        int mergeSize = firstListMerge.size() + secondListMerge.size();
        List<Integer> resultList = new ArrayList<Integer>(mergeSize);
        for (int i = 0; i < mergeSize; i++) {
            //        1 сравниваем эти два элемента между собой
            if (firstArrayIndex < firstListMerge.size() && secondArrayIndex < secondListMerge.size()) {
                if ( firstListMerge.get(firstArrayIndex) <  secondListMerge.get(secondArrayIndex)) {
                    //меньший из них записываем в результат
                    //Берем следующий элемент массива  вместо того которого мы записали в результат
                    resultList.add(firstListMerge.get(firstArrayIndex++));
                } else {
                    resultList.add(secondListMerge.get(secondArrayIndex++));
                }
                //            переходим на 1 до тех пор пока в одном из масивов не закончатся элементы
            } else if (firstArrayIndex < firstListMerge.size()) {
                //определяем в каком массиве остались элементы
                //С этого массыва все оставшиеся элементы записываем в результат
                resultList.add(firstListMerge.get(firstArrayIndex++));
            } else if (secondArrayIndex < secondListMerge.size()) {
                resultList.add(secondListMerge.get(secondArrayIndex++));
            }
        }
//        int indexmass= 0;
//        while(indexmass < mergeSize){
//            indexmass++;
//            if (firstArryElement < firstListMerge.size() && secondArryElement < secondListMerge.size()) {
//                if (firstArryIndex < secondArryIndex) {
//                    //меньший из них записываем в результат
//                    //Берем следующий элемент массива  вместо того которого мы записали в результат
//                    resultList.add(firstListMerge.get(firstArryIndex++));
//                } else {
//                    resultList.add(secondListMerge.get(secondArryIndex++));
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
            List<Integer> result0 = (List<Integer>) subTask0.join();
            List<Integer> result1 = (List<Integer>) subTask1.join();

            //            объединяем с сортировкой результат подзадач и возвращаем его головной задаче
            return mergelist(result0, result1);
        }
    }
}





