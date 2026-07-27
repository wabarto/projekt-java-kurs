import java.math.BigDecimal;
import java.util.Scanner;

public class VariablesDemo {
    public static void main(String[] args) {
        byte b1 = 127; // 8 bitow
        byte b2 = -128;
//        short s1 = 32767; // 16 bitow
//        short s2 = -32768;

        int i = 2_000_000; // 32 bity

        long l = 2_000_000_000L; // 64 bity

        float f = 6.15f; // 32 bity, 7 cyfr precyzji

        double d = 3.14; // 64 bity, 15 cyfr precyzji

        boolean active = true; // true / false

        char letter = 'A';

        int code = letter;

        String s = "String";

        int[] array = {1, 2, 3};


        int a = 5;
        int b = a;
        b = 10;

//        System.out.println(a);

        int [] arrayB = array;
        arrayB[0] = 5;

//        System.out.println(array[0]);

        String s1 = "String1";
        String s2 = s1;

        s2 = s2 + "String2";
//        System.out.println(s1);

        double sum = 0.1 + 0.2;
//        System.out.println(sum);
//        System.out.println(code);


        int maxInt = Integer.MAX_VALUE;
        System.out.println(maxInt);

//        2147483647 + 1 = 2147483678
        System.out.println(maxInt + 1);


        int x = 5;
        System.out.println("x++ = " + x++);
        System.out.println("x = " + x);
        System.out.println("++x = " + ++x);


        int aa = 12; // binarnie 1100
        int bb = 10; // binarnie 1010
        System.out.println(aa & bb);

        // 1 AND 1 = 1

        int cc = 8; // 1000

        // | OR bitowy

        System.out.println(aa | bb);

        // 14 = 1110

        // ^ XOR bitowy

        // rozne bity = 1, takie same = 0

        System.out.println(aa ^ bb);
        // 0110 = 6

        // ~
        System.out.println(~aa);
        // -13

        // << left shift = mnozenie przez 2^n

        System.out.println(1 << 3); // 1000 = (1 * 2^3)

        System.out.println(8 >> 2); // 8 / 2^2  - 0010

        System.out.println(aa >>> 2); // 1100 -> 0011 = 3


        // Scanner - wczytanie od użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swoje imię: ");
        String name = scanner.nextLine();
        System.out.print("Podaj swój wiek: ");
        int age = scanner.nextInt();



    }
}
