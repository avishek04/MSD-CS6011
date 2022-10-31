public class myRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i == 10) System.out.print("\n");
            System.out.print("hello number " + i + " from thread number " + Thread.currentThread().getId() + " ");
        }
    }
}
