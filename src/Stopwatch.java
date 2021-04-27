public class Stopwatch implements Runnable{

   private int mSec, sec, min;
   private Thread thread;
   private GUI gui;

   public Stopwatch(String s, GUI gui){
       thread = new Thread(this);
       String [] mSec = s.split("\\.");
       String [] minSec = mSec[0].split(":");


       this.min = Integer.parseInt(minSec[0]);
       this.sec = Integer.parseInt(minSec[1]);
       this.mSec = Integer.parseInt(mSec[1]);
       this.gui = gui;
       thread.start();
   }

    @Override
    public void run() {
        while(min < 61 && !gui.exit){
            if(gui.lapped){
                gui.lapped = false;
            }
            else
            {
                mSec ++;
                if(mSec == 100){
                    mSec = 0;
                    sec++;
                }
                if(sec == 60){
                    sec = 0;
                    min++;
                }
                if(min >= 60){
                    thread.stop();
                }
                gui.updateViewSW(min+":"+sec+"."+mSec);
                System.out.println(min+":"+sec+"."+mSec);
                try{
                    Thread.sleep(10);
                }catch(InterruptedException er){
                    er.printStackTrace();
                }
            }
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }

}

