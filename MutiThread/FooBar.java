package MutiThread;


/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 * 1115. 交替打印FooBar
 */
public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    private volatile boolean flag = true;

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!flag){
                Thread.sleep(3);
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            flag = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (flag){
                Thread.sleep(3);
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            flag = true;
        }
    }


/*        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (lock.tryLock())

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            flag = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (flag){
                Thread.sleep(3);
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            flag = true;
        }
    }*/
}
