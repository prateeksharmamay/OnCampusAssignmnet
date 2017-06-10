import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prateek on 6/5/2017.
 */
public class HeartBeatMonitor {
    private List<Integer> xValues;
    private List<Integer> yValues;
    private List<Integer> hrChangeList;
    private int threshold;

    public HeartBeatMonitor(){
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        hrChangeList = new ArrayList<>();
        threshold = 0;
    }

    /*
        To Calculate the rate with which heart beats change with time
     */
    public void hearRateChange(){
        int hrChangeValue;
        for(int i = 0 ; i < xValues.size()-1 ; i++){
            int denominator =  xValues.get(i) - xValues.get(i+1);
            int numerator = yValues.get(i) - yValues.get(i+1);
            hrChangeValue = Math.abs(numerator /denominator);

            hrChangeList.add(hrChangeValue);
        }

        for(int i = 0 ; i < hrChangeList.size(); i++){
            System.out.println(hrChangeList.get(i));
        }
    }

    /*
        To compute the rate with which heart beat change during warmup
     */
    public int getHRCWarmup(){
        int denominator =  xValues.get(0) - xValues.get(1);
        int numerator = yValues.get(0) - yValues.get(1);
        int hrChangeValue = Math.abs(numerator /denominator);

        return hrChangeValue;
    }

    /*
        To compute the rate with which heart beat change during cooldown
     */
    public int getHRCCooldown(){
        int denominator =  xValues.get(2) - xValues.get(3);
        int numerator = yValues.get(2) - yValues.get(3);
        int hrChangeValue = Math.abs(numerator /denominator);

        return hrChangeValue;
    }

    /*
        To compute the time for which Serena's Heart beat remained above the threshold
     */
    public int getHRTime(){
        int hrBelowThreshold, hrAboveThreshold;
        int hrcCooldown = getHRCCooldown();
        int hrcWarmup = getHRCWarmup();

        hrBelowThreshold = xValues.get(2) + ((yValues.get(2)-threshold) / hrcCooldown);
        hrAboveThreshold = (threshold - yValues.get(0)) / hrcWarmup;

        return (hrBelowThreshold - hrAboveThreshold);
    }

    /*
        To display the graph showing the slope for available time and heart beat
     */
    public void displayGraph(){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XYLineChartExample(xValues,yValues).setVisible(true);
            }
        });
    }

    // Getters and Setters to access private class attributes
    public List<Integer> getxValues() {
        return xValues;
    }

    public void setxValues(List<Integer> xValues) {
        this.xValues = xValues;
    }

    public List<Integer> getyValues() {
        return yValues;
    }

    public void setyValues(List<Integer> yValues) {
        this.yValues = yValues;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
