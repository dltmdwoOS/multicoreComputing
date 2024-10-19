public class lec3{
    public static void main(String[] args) {
        MyThread1 mt1 = new MyThread1();
        MyThread2 mt2 = new MyThread2(mt1);
        

        System.out.println("Strat");
        mt2.start();
    }
}

class MyThread1 extends Thread{
    public void run(){
        System.out.println(getName()+" is running...");
        for (int i=0;i<4;i++){
            System.out.println("Hello there, from "+getName());
        }
    }
}

class MyThread2 extends Thread{
    private MyThread1 wait4me;
    public MyThread2(MyThread1 target){
        super();
        wait4me = target;
    }
    public void run(){
        System.out.println(getName()+" is waiting for "+wait4me.getName());
        try{
            wait4me.join();
        }
        catch (InterruptedException e){
            System.out.println(wait4me.getName()+" has finished");
        }
        for (int i=0;i<4;i++){
            System.out.println("Hello there, from "+getName());
        }
    }
}