import java.sql.Array;

class Main {
    public static int answer;

    public static void badSum() {
        answer = 0;

        Thread[] array = new Thread[10];

        for (int i = 0; i < array.length; i++) {
            final int finalI = i;
            sumRunnable run = new sumRunnable(finalI, 10);
            array[i] = new Thread(run);
            array[i].start();
            try {
                array[i].join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        badSum();
        System.out.println(answer);
        System.out.println((40000 * (40000 - 1) / 2));
//        Thread[] array = new Thread[10];
//
//        for (int i = 0; i < array.length; i++) {
//            myRunnable run = new myRunnable();
//            array[i] = new Thread(run);
//            array[i].start();
//            try {
//                array[i].join();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
