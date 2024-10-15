class MyRunnable1 implements Runnable {
    private String name;

    public MyRunnable1(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 1; i <= 6; i++) {
            System.out.println("Run method: " + name);
        }
    }
}

public class ThreadEx {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable1("MyThread obj1"));
        Thread thread2 = new Thread(new MyRunnable1("MyThread obj2"));
        
        thread1.start();
        thread2.start();
    }
}
