public class sumRunnable implements Runnable {
    private int start;
    private int end;
    sumRunnable(int i, int threadCount) {
        start = i * 40000 / threadCount;
        end = Math.min((i+1) * 40000/ threadCount, 40000);
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            Main.answer += i;
        }
    }
}
