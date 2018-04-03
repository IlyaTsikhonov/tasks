package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            reverseMapping("src\\task2\\in.txt", "src\\task2\\out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Считывает текст из одного файла и записывает отображение в другой файл</p>
     *
     * @param inFileName - путь к файлу с исходным текстом
     * @param outFileName - путь к файлу, в который нужно записать отображение
     */
    private static void reverseMapping(String inFileName, String outFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inFileName));//считываем все строки из входного файла
        byte[] lineAsByteArray;
        byte[] reverseByteArray;
        int listSize = lines.size();
        int lineLength;
        for (int i = 0; i < listSize; i++) {
            lineAsByteArray = lines.get(i).getBytes();//преобразуем строку в байты
            lineLength = lineAsByteArray.length;
            reverseByteArray = new byte[lineLength];
            for (int j = 0; j < lineLength; j++) {
                reverseByteArray[j] = lineAsByteArray[lineLength - j - 1];//зеркально отражаем строку
            }
            lines.set(i, new String(reverseByteArray));//перезаписываем новую строку в список
        }
        Files.write(Paths.get(outFileName), lines);//записываем все строки по порядку в выходной файл
    }
}
