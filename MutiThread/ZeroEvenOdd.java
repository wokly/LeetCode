package MutiThread;


import java.util.concurrent.locks.ReentrantLock;

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private volatile boolean zero = true;
    private volatile boolean even = true;
    private  int i = 1;
    ReentrantLock lock = new ReentrantLock(true);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        if(i>n)
            return;
        while (!zero){
            Thread.yield();
        }
        lock.lock();
        try {
                printNumber.accept(0);
            zero = false;
            }finally {
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        if(i>n)
            return;
        while (zero){
            Thread.yield();
        }
        lock.lock();
        try {
            while (!even){
                lock.unlock();
                Thread.yield();
            }
            printNumber.accept(i++);
            even = false;
            zero = true;
        }finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        if(i>n)
            return;
        while (zero){
            Thread.yield();
        }
        lock.lock();
        try {
            while (even){
                lock.unlock();
                Thread.yield();
            }
            printNumber.accept(i++);
            even = true;
            zero = true;
        }finally {
            lock.unlock();
        }
    }


    private class IntConsumer {
        void accept(int x) {
            System.out.println(x);
        }
    }
}
