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

