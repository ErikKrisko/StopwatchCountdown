public class Countdown implements Runnable {

    private Thread thread;
    private int sec;
    private GUI gui;

    public Countdown(int i, GUI gui){
        thread = new Thread(this);
        this.sec = i;
        this.gui = gui;
        thread.start();
    }

    @Override
    public void run() {
        while(sec > 0 && !gui.cdExit){
            System.out.println(sec);
            sec--;
            gui.updateViewCD(sec);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException er){
                er.printStackTrace();
            }
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
