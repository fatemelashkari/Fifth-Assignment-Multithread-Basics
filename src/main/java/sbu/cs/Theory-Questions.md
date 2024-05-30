### First Code :
```

public static class SleepThread extends Thread {
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
            } finally {
                System.out.println("Thread will be finished here!!!");
            }
        }
    }

    public static void main(String[] args) {
        SleepThread thread = new SleepThread();
        thread.start();
        thread.interrupt();
    }

```
### Output :
#### "Thread was interrupted!"
#### "Thread will be finished here!!!"


### Why :
#### in this provided code inside the main method we call the start method to start the threads, so it means the thread is trying to be run then we call the interrupt() method which will interrupt the function of the thread and it makes an exception, why ? because the thread is running then we interrupt its function so the output will be : "Thread was interrupted!" and then "Thread will be finished here!!!".
### Second Code :
```
public class DirectRunnable implements Runnable {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        DirectRunnable runnable = new DirectRunnable();
        runnable.run();
    }
}
```

### Output :
#### Running in: main
### Why :
#### when we do not initialize a new Thread it will consider the default thread means main thread then the current thread will be main thread
### Third Code :
```
public class JoinThread extends Thread {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        JoinThread thread = new JoinThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Back to: " + Thread.currentThread().getName());
    }
}
```
### Answer :
#### because the join() method causes this event :  the calling thread to wait for the specified thread to be finished :
#### 1. we are taking an object from that class which will run the thread
#### 2. we call start() method for that thread
#### 3. then we are calling the join() method for that thread which is meaning this that the calling thread will wait for the specific thread to be ran and executed then it will continue which in this code the Mian thread will wait for the object thread to be executed then the Main thread will continue****

