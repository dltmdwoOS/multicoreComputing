class MyRunnable2 implements Runnable {
    private String name;

    public MyRunnable2(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("Run() method\n"+name+" has priority: "+Thread.currentThread().getPriority());
    }
}

public class PriorityEx {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable2("Thread-0"));
        Thread t2 = new Thread(new MyRunnable2("Thread-1"));
        Thread t3 = new Thread(new MyRunnable2("Thread-2"));

        t1.setPriority(Thread.MAX_PRIORITY);  
        t3.setPriority(Thread.MIN_PRIORITY);  

        t1.start();
        t2.start();
        t3.start();
    }
}
