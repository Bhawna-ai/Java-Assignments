public class Loader implements Runnable {
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            System.out.println();
        } catch (Exception e) {}
    }
}

