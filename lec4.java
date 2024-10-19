public class lec4 {
    public static void main(String[] args) {
        Account myacc = new Account();
        MyUser user1 = new MyUser(myacc);
        MyUser user2 = new MyUser(myacc);
        MyUser user3 = new MyUser(myacc);
        user1.start();
        user2.start();
        user3.start();
    }
}

class Account{
    private int balance=0;
    public void deposit(int val){
        synchronized (this){
            balance += val;
            System.out.println("Deposited "+balance);
        }
        
    }
    public synchronized void withdraw(int val){
        balance -= val;
        System.out.println("Withdrawed "+balance);
    }
}

class MyUser extends Thread{
    private Account myAccount;
    public MyUser(Account acc){
        super();
        myAccount = acc;
    }
    public void run(){
        myAccount.deposit(500);
        myAccount.withdraw(500);
    }
}