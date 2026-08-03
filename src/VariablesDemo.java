import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class VariablesDemo {
    public static void main(String[] args) {
//        byte b1 = 127; // 8 bitow
//        byte b2 = -128;
////        short s1 = 32767; // 16 bitow
////        short s2 = -32768;
//
//        int i = 2_000_000; // 32 bity
//
//        long l = 2_000_000_000L; // 64 bity
//
//        float f = 6.15f; // 32 bity, 7 cyfr precyzji
//
//        double d = 3.14; // 64 bity, 15 cyfr precyzji
//
//        boolean active = true; // true / false
//
//        char letter = 'A';
//
//        int code = letter;
//
//        String s = "String";
//
//        int[] array = {1, 2, 3};
//
//
//        int a = 5;
//        int b = a;
//        b = 10;
//
////        System.out.println(a);
//
//        int [] arrayB = array;
//        arrayB[0] = 5;
//
////        System.out.println(array[0]);
//
//        String s1 = "String1";
//        String s2 = s1;
//
//        s2 = s2 + "String2";
////        System.out.println(s1);
//
//        double sum = 0.1 + 0.2;
////        System.out.println(sum);
////        System.out.println(code);
//
//
//        int maxInt = Integer.MAX_VALUE;
//        System.out.println(maxInt);
//
////        2147483647 + 1 = 2147483678
//        System.out.println(maxInt + 1);
//
//
//        int x = 5;
//        System.out.println("x++ = " + x++);
//        System.out.println("x = " + x);
//        System.out.println("++x = " + ++x);
//
//
//        int aa = 12; // binarnie 1100
//        int bb = 10; // binarnie 1010
//        System.out.println(aa & bb);
//
//        // 1 AND 1 = 1
//
//        int cc = 8; // 1000
//
//        // | OR bitowy
//
//        System.out.println(aa | bb);
//
//        // 14 = 1110
//
//        // ^ XOR bitowy
//
//        // rozne bity = 1, takie same = 0
//
//        System.out.println(aa ^ bb);
//        // 0110 = 6
//
//        // ~
//        System.out.println(~aa);
//        // -13
//
//        // << left shift = mnozenie przez 2^n
//
//        System.out.println(1 << 3); // 1000 = (1 * 2^3)
//
//        System.out.println(8 >> 2); // 8 / 2^2  - 0010
//
//        System.out.println(aa >>> 2); // 1100 -> 0011 = 3
//
//
//        // Scanner - wczytanie od użytkownika
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Podaj swoje imię: ");
//        String name = scanner.nextLine();
//        System.out.print("Podaj swój wiek: ");
//        int age = scanner.nextInt();

//        int x = 10;
//        if (x > 5) {
//            x = x * 2;
//        }
//        if (x > 15) x = x + 100;
//
//
//        int day = 3;
//
//        String dayName;
//
//        switch (day) {
//            case 1: dayName = "Poniedzialek"; break;
//            case 2: dayName = "Wtorek"; break;
//            default: dayName = "Inny"; break;
//        }
//
//        String dayNameNew = switch (day) {
//            case 1 -> "Poniedzialek";
//            case 2 -> "Wtorek";
//            default -> {
//                System.out.println("to nie zly dzien tygodnia");
//                yield "Inny";
//            }
//        };
//
//
//
//        int y = 10;
//
//        String category = y > 5 || x < 5 ? "Wiekszy" : "Mniejszy";
//
//
//        System.out.println(dayName);
//        System.out.println(dayNameNew);
//
//
//
//        // loops
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(i);
//        }
//
//
//        for (int i = 0; i < 5; i--) {
//            System.out.println(i);
//        }
//
//        for (int i = 0; i < 5; i += 2) {
//            System.out.println(i);
//        }
//
//        int found = 2;
//
//        for (int i = 0; i < 5; i++) {
//            if (i == found) {
//                System.out.println(found);
//                break;
//            }
//        }
//
//        for (int i = 0; i < 5; i++) {
//            if (i % 2 != 0) {
//                System.out.println("Nieparzysta");
//                continue;
//            } else {
//                System.out.println("Parzysta");
//            }
//
//            System.out.println("Inna");
//        }
//
//
//        int [] numbers = {10, 20, 30};
//
//        for (int num : numbers) {
//            System.out.println(num);
//        }
//
//
//        for (int i = 0, j = 10; i < j; i++, j--) {
//            System.out.println(j + " " + i);
//        }
//
//
//        int n = 1000;
//
//        for (int i = 0; i < n; i++) {
//            System.out.println(i);
//
//            for (int j = 0; j < n; j++) {
//                System.out.println(j);
//            }
//        }
//
//        //while
//
////        int n = 0;
//
//        while (n > 5) {
//            System.out.println(n);
//            n++;
//        }
//
//
//        // do-while
//
//        int count = 0;
//
//        do {
//            System.out.println(count);
//            count++;
//        } while (count < 3);
//
//
//        // tablice
//
//
////        // 1 sposob
////        int[] numbers = {10, 20, 30, 40};
//
//
//        // 2 sposob
//        int[] scores = new int[5]; // 0 0 0
//
//        boolean[] flags = new boolean[3]; // false false false
//
//        String[] names = new String[4]; // null null null
//
//
//
//        // 3 sposob
//        int[] data;
//
//        data = new int[] {1, 2, 3, 4};
//
//
//        // data[5] ArrayIndexOutOfBoundsException
//
//
//        System.out.println(data.length);
//
//        // String.length()
//
//
//
//        // kopiowanie
//
//        int[] original = {1, 2, 3};
//        int[] copy = original;
//        copy[0] = 100;
//        System.out.println(original[0]);
//
//
//        int [] validCopy = Arrays.copyOf(original, original.length);
//
//        validCopy[0] = 120;
//        System.out.println(original[0]); // 1
//
//        Arrays.sort(original);
//
//        Integer[] integers = {1, 2, 3, 4, 5};
//
//        Arrays.sort(integers, Collections.reverseOrder());
//        Arrays.fill(original, 0);
//        Arrays.copyOfRange(original, 0, 2);
//
//
//        for (int i = 0, j = original.length - 1; i < j; i++, j--) {
//            int temp = original[i];
//            original[i] = original[j];
//            original[j] = temp;
//        }
//
        int [][] matrix = {
                {1, 2, 3},
                {11, 3, 6},
                {7, 8, 1}
        };
//
//        System.out.println(matrix[0][0]);
//        System.out.println(matrix[2][1]);


//        Arrays.sort(matrix, Comparator.comparingInt(row -> row[0]));



//        int [] temp = new int[matrix.length * matrix[0].length];

//        int index = 0;
//
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < matrix[row].length; col++) {
//                temp[index] = matrix[row][col];
//                index++;
//            }
//        }
//
//        int [] temp = Arrays.stream(matrix)
//                .flatMapToInt(Arrays::stream)
//                .sorted()
//                .toArray();
//
//        Arrays.sort(temp);
//
//        index = 0;
//
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < matrix[row].length; col++) {
//                matrix[row][col] = temp[index++];
//            }
//        }

        for (int [] row : matrix) {
            for (int val : row) {
                System.out.println(val);
            }
        }



    }
}
