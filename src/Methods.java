public class Methods {
    public static void main(String[] args) {
        calculate(3);


        // step 1 Przed: 3 -> calculate(1)
        // step 2 Przed: 2 -> calculate(1)
        // step 3 Przed: 1 -> calculate(0)
        // step 4 finish

        // calculate(1) -> Po: 1
        // calculate(2) -> Po: 2
        // calculate(3) -> Po: 3


        factorial(4);

        // step 1 factorial(4) = 4 * factorial(3)
        // step 2 factorial(3) = 3 * factorial(2)
        // step 3 factorial(2) = 2 * factorial(1)
        // step 4 factorial(1) = 1


        // 4 * factorial(3) = 4 * 3 * factorial(2) = 4 * 3 * 2 * factorial(1) = 4 * 3 * 2 * 1 = 24


        // stos
        // factorial(1)
        // factorial(2)
        // factorial(3)
        // factorial(4)
    }

    static void print(int n) {
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
    }


    static void calculate(int n) {
        if (n == 0) {
            return;
        }

        System.out.println("Przed: " + n);
        calculate(n - 1);
        System.out.println("Po: " + n);
    }

    // 4! 4*3*2*1

    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
