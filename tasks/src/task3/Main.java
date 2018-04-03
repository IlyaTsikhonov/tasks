package task3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[Task.ARRAY_LENGTH];
        Arrays.parallelSetAll(array, v -> v % 9 + 1);//заполняем массив array[i] = i % 9 + 1 (для примера)

        ExecutorService exec = Executors.newFixedThreadPool(Task.TASK_COUNT);//создаем службу исполнителей на 10 потоков
        List<Task> tasks = Task.getTaskList(array);//получаем все потоки
        List<Future<BigInteger>> results = exec.invokeAll(tasks);//получаем Future для каждого потока
        BigInteger mul = BigInteger.ONE;
        try {
            for (Future<BigInteger> result : results) {
                mul = mul.multiply(result.get());//дожидаемся завершения потоков и умножаем результаты
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();//завершаем службу исполнителей
        }
        System.out.println("Multiply is " + mul);//выводим результат
    }
}
