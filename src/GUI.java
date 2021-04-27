import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    JLabel info = new JLabel("Laps:");

    //TextBox
    JTextField countdown = new JTextField();
    JTextField stopwatch = new JTextField("0:0.0");

    //Buttons
    JButton countdownB = new JButton("Countdown");
    JButton stopwatchB = new JButton("Stopwatch");
    JButton lapB = new JButton("Lap");
    JButton stopSW = new JButton("Stop");

    //Info Text Area
    TextArea infoField = new TextArea();

    int min = 0;
    int sec = 0;
    int mSec = 0;
    int lap = 0;
    String lapField = "";
    Boolean exit = false;
    Boolean swFirst = false;
    Boolean cdExit = false;
    Boolean lapped = false;

    public GUI() {

        this.setLayout(null);

        countdownB.setBounds(340, 40, 100, 25);
        countdown.setBounds(250,40, 80,25);

        stopwatchB.setBounds(340, 80, 100, 25);
        lapB.setBounds(455, 80, 80, 25);
        stopSW.setBounds(455, 120, 80, 25);
        stopwatch.setBounds(250, 80, 80, 25);

        info.setBounds(50, 160, 40, 25);
        infoField.setBounds(100, 160, 440, 250);

        this.add(info);
        this.add(infoField);
        this.add(countdownB);
        this.add(stopwatchB);
        this.add(countdown);
        this.add(stopwatch);
        this.add(lapB);
        this.add(stopSW);

        lapB.addActionListener(this);
        stopSW.addActionListener(this);
        countdownB.addActionListener(this);
        stopwatchB.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object target = e.getSource();
        if(target == countdownB) {
            cdExit = false;
            new Countdown(Integer.parseInt(countdown.getText()), this);
        }

        if(target == stopwatchB){
            if(!swFirst){
                lapField = "";
                exit = false;
                lap = 0;
                stopwatch.setText("0:0.0");
                new Stopwatch(stopwatch.getText(),this);
                swFirst = true;
            }else{
                exit = true;
                swFirst = false;
            }
        }

        if(target == lapB){
            lap++;
            lapField += "Lap: " + lap + " Time: " + stopwatch.getText() + "\n";
            infoField.setText(lapField);

            lapped = true;
            min = 0; sec = 0; mSec = 0;
        }

        if(target == stopSW){
            exit = true;
            cdExit = true;
            stopwatch.setText("0:0.0");
            lap = 0;
            lapField = "";
        }
    }

    public void updateViewCD(int i){
        countdown.setText(String.valueOf(i));
    }

    public void updateViewSW(String s){ stopwatch.setText(s); }

}



