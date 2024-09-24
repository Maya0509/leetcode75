package multi_thread;

import java.util.concurrent.CountDownLatch;

public class Solution1114PrintInOrder {
    class Foo {
        private CountDownLatch countDownLatch1;
        private CountDownLatch countDownLatch2;
        public Foo() {
            countDownLatch1 = new CountDownLatch(1);
            countDownLatch2 = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            countDownLatch1.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            countDownLatch1.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            countDownLatch2.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            countDownLatch2.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }


    class Foo2 {

        private boolean oneDone;

        private boolean twoDone;



        public Foo2() {

            oneDone = false;

            twoDone = false;

        }



        public synchronized void first(Runnable printFirst) throws InterruptedException {

            printFirst.run();

            oneDone = true;

            notifyAll();

        }



        public synchronized void second(Runnable printSecond) throws InterruptedException {

            while (!oneDone) {

                wait();

            }

            printSecond.run();

            twoDone = true;

            notifyAll();

        }



        public synchronized void third(Runnable printThird) throws InterruptedException {

            while (!twoDone) {

                wait();

            }

            printThird.run();

        }

    }

}
