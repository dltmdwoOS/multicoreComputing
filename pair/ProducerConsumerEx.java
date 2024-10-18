public class ProducerConsumerEx{
    public static void main(String[] args) {
        Shop c = new Shop();
        Producer p1 = new Producer(c, 1);
        Producer p2 = new Producer(c, 1);
        Producer p3 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);
        Consumer c2 = new Consumer(c, 1);
        p1.start();
        p2.start();
        p3.start();
        c1.start();
    }
}
class Shop{
    private int materials;
    public synchronized int get() throws InterruptedException{
        while (materials<=0) { // if (materials<=0) <<<<< Bug#1을 일으킴
            this.wait(); // wait()을 써도 됨.
        }
        materials--;
        System.out.println("Consumed value 1 get : " + materials);
        this.notify(); // notify()을 써도 됨.
        return materials;
    }
    public synchronized void put(int value) throws InterruptedException {
        while (materials>=10) {
            this.wait();
        }
        materials++;
        System.out.println("Produced value 1 put : " + materials);
        this.notify();
    }
}
class Consumer extends Thread{
    private Shop shop;
    private int number;
    public Consumer(Shop c, int number){
        shop = c;
        this.number = number;
    }
    public void run(){
        for (int i = 0; i < 10; i++) { 
            try {
                shop.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread{
    private Shop shop;
    private int number;
    public Producer(Shop c, int number){
        shop = c;
        this.number = number;
    }
    public void run() {
        for (int i = 0; i < 5; i++) { 
            try {
                shop.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}