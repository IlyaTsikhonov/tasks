package task3;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Computer {
    private int[] array;//обрабатываемый массив

    /**
     * <p>Конструктор - создание нового объекта с определенными значениями</p>
     *
     * @param array - обрабатываемый массив целых чисел
     */
    public Computer(int[] array) {
        this.array = array;
    }

    /**
     * <p>Умножает все элементы массива</p>
     *
     * @return результат умножения
     */
    public BigInteger getMultiply() {
        ExecutorService exec = Executors.newFixedThreadPool(Task.TASK_COUNT);//создаем службу исполнителей на 10 потоков
        List<Task> tasks = Task.getTaskList(array);//получаем все потоки
        BigInteger mul = BigInteger.ONE;
        try {
            List<Future<BigInteger>> results = exec.invokeAll(tasks);//получаем Future для каждого потока
            for (Future<BigInteger> result : results) {
                mul = mul.multiply(result.get());//дожидаемся завершения потоков и умножаем результаты
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();//завершаем службу исполнителей
        }
        return mul;
    }
}
