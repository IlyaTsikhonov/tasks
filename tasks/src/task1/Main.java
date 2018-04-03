package task1;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();//считываем строку
        System.out.print(content + (isBalanced(content) ? " is balanced" : " is not balanced"));
    }

    /**
     * <p>Проверяет строку на сбалансированность</p>
     *
     * @param content - проверяемая строка
     * @return true, если строка сбалансирована, и false, если не сбалансирована
     */
    private static boolean isBalanced(String content) {
        Stack<Character> stack = new Stack<>();

        for (char symbol : content.toCharArray()) {
            switch(symbol) {
                case '(':
                    stack.push(symbol);//заносим открывающую скобку в вершину стека
                    break;
                case ')':
                    if(stack.isEmpty() || !stack.pop().equals('(')) {//если перед закрывающей скобкой нет открывающей
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
