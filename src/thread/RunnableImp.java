package thread;

public class RunnableImp implements Runnable {
    private int ticket=100;
    @Override
    public void run() {
        while (true){
            if (ticket>0) {
                System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket + "张票");
                ticket--;
            }
        }

    }
}
