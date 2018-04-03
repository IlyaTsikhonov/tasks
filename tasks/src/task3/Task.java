package task3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Task implements Callable<BigInteger> {
    public static final int TASK_COUNT = 10;//количество потоков
    public static final int ARRAY_LENGTH = 100000;//размер массива
    private static final int RANGE = ARRAY_LENGTH / TASK_COUNT;//диапазон для потоков

    private int[] array;//массив целых чисел
    private int number;//номер потока

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param array - обрабатываемый массив целых чисел
     * @param number - номер потока
     */
    private Task(int[] array, int number) {
        this.array = array;
        this.number = number;
    }

    /**
     * <p>Умножает все элементы массива</p>
     *
     * @return результат умножения
     */
    @Override
    public BigInteger call() {
        BigInteger result = BigInteger.ONE;//результат умножения
        int startPos = number * RANGE;
        int endPos = startPos + RANGE;
        for (int i = startPos; i < endPos; i++) {
            result = result.multiply(new BigInteger(Integer.toString(array[i])));//result *= array[i]
        }
        return result;
    }

    /**
     * <p>Создает и возвращает список потоков, готовых к выполнению</p>
     *
     * @param array обрабатываемый массив целых чисел
     * @return список потоков, готовых к выполнению
     */
    public static List<Task> getTaskList(int[] array){//список из 10 потоков
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < TASK_COUNT; i++) {
            taskList.add(new Task(array, i));
        }
        return taskList;
    }
}
