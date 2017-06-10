import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Prateek on 6/5/2017.
 */
public class HeartBeatMonitorImpl {
    public static void main(String args[]){

        System.out.println("WELCOME TO HEART BEAT MONITOR:");

        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        int HRThreshold;
        Scanner scan = new Scanner(System.in);


        HeartBeatMonitor hr = new HeartBeatMonitor();

        int i = 1;

        do{
            System.out.println(i + ")");

            System.out.print("Enter time: ");
            xCoords.add(scan.nextInt());

            System.out.print("Enter Heart Rate: ");
            yCoords.add(scan.nextInt());
            i++;

            /*System.out.println("Do you want to add more: (y/n)");
            ans = scan.next().charAt(0);*/

        }while(i <= 4);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Enter the threshold Heart Beat: ");
        HRThreshold = scan.nextInt();

        hr.setxValues(xCoords);
        hr.setyValues(yCoords);
        hr.setThreshold(HRThreshold);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Following table shows the Heart Rate Change");
        hr.hearRateChange();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Hear Rate Change during Warmup:");
        System.out.println(hr.getHRCWarmup());

        System.out.println("--------------------------------------------------------------");
        System.out.println("Hear Rate Change during Cooldown:");
        System.out.println(hr.getHRCCooldown());

        System.out.println("--------------------------------------------------------------");
        System.out.println("Time for which Heart Rate was at or above Threshold:");
        System.out.println(hr.getHRTime());

        System.out.println("--------------------------------------------------------------");
        System.out.println("Graph showing the Heart Rate over Time:");
        hr.displayGraph();

        scan.close();
    }
}
