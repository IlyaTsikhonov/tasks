package task3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[Task.ARRAY_LENGTH];
        Arrays.parallelSetAll(array, v -> v % 9 + 1);//заполняем массив array[i] = i % 9 + 1 (для примера)
        System.out.println("Multiply is " + new Computer(array).getMultiply());//выводим результат
    }
}
