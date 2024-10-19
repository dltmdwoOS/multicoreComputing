public class ProducerConsumerEx{
    public static void main(String[] args) {
        Shop c = new Shop();
        Producer p1 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);
        p1.start();
        c1.start();
    }
}
class Shop{
    private int materials;
    private boolean available = false;
    public synchronized int get() throws InterruptedException{
        while (!available) {
            wait();
        }
        System.out.println("Consumed value 1 get : " + materials);
        available = false;
        notify();
        return materials;
    }
    public synchronized void put(int value) throws InterruptedException {
        while (available) {
            wait();
        }
        materials = value;
        System.out.println("Produced value 1 put : " + materials);
        available = true;
        notify();
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
        for (int i = 0; i < 10; i++) { 
            try {
                shop.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}