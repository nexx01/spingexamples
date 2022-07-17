import java.util.Scanner;

class Scratch {

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        while (!sc.hasNextInt() || isValidNumber(sc.nextInt())) {
            sc.nextLine();
            System.out.println("Неверное число");
            System.out.println("Введите от 0 до 9");
        }

    }

    private static boolean isValidNumber(int shoot) {
        return shoot < 0 || shoot > 9;
    }

}