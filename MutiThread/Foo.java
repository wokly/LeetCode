package MutiThread;

class Foo {

    public Foo() {

    }

    private volatile int i = 1;

    public void first(Runnable printFirst) throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (i == 1) {
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                i = 2;
                flag = false;
            } else {
                Thread.sleep(1000);
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (i == 2) {
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                i = 3;
                flag = false;
            } else {
                Thread.sleep(1000);
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (i == 3) {
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                i = 4;
                flag = false;
            } else {
                Thread.sleep(1000);
            }
        }
    }
}