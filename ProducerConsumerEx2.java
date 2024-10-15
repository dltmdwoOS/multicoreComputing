public class ProducerConsumerEx2 {
    public static void main(String[] args) {
        Shop2 shop = new Shop2();
        Producer2 producerA = new Producer2(shop, "A");
        Producer2 producerB = new Producer2(shop, "B");
        Consumer2 consumer = new Consumer2(shop);
        
        producerA.start();
        producerB.start();
        consumer.start();
    }
}

class Shop2 {
    private int itemA;
    private int itemB;
    private boolean isAAvailable = false;
    private boolean isBAvailable = false;
    public synchronized void consume() throws InterruptedException {
        while (!isAAvailable || !isBAvailable) {
            wait();
        }
        
        System.out.println("Consumed item from A: " + itemA);
        System.out.println("Consumed item from B: " + itemB);
        
        isAAvailable = false;
        isBAvailable = false;
        
        notifyAll(); 
    }

    public synchronized void produceA(int value) throws InterruptedException {
        while (isAAvailable) {
            wait();
        }
        itemA = value;
        isAAvailable = true;
        System.out.println("Produced item A: " + itemA);
        notifyAll();
    }

    public synchronized void produceB(int value) throws InterruptedException {
        while (isBAvailable) {
            wait();
        }
        itemB = value;
        isBAvailable = true;
        System.out.println("Produced item B: " + itemB);
        notifyAll(); 
    }
}

class Consumer2 extends Thread {
    private Shop2 shop;

    public Consumer2(Shop2 shop) {
        this.shop = shop;
    }

    public void run() {
        for (int i = 0; i < 10; i++) { 
            try {
                shop.consume(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer2 extends Thread {
    private Shop2 shop;
    private String type;

    public Producer2(Shop2 shop, String type) {
        this.shop = shop;
        this.type = type;
    }

    public void run() {
        for (int i = 0; i < 10; i++) { 
            try {
                if (type.equals("A")) {
                    shop.produceA(i);
                } else if (type.equals("B")) {
                    shop.produceB(i); 
                }
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
