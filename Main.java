// github:akin101
// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};
    static int[][][] profits = new int[MONTHS][DAYS][COMMS];

    public static void loadData() {
        System.out.println("Working dir = " + System.getProperty("user.dir"));

        for(int m = 0; m < MONTHS ; m++){
            String fileName = "Data_Files/" + months[m] + ".txt";
            try{
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line = br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    int day = Integer.parseInt(parts[0].trim()) - 1;
                    String comm = parts[1].trim();
                    int profit = Integer.parseInt(parts[2].trim());

                    for (int c = 0; c < COMMS; c++) {
                        if (commodities[c].equals(comm)) {
                            profits[m][day][c] = profit;
                            break;
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }
        int maxProfit = Integer.MIN_VALUE;
        String bestCommodity = "";

        for (int c = 0; c < COMMS; c++) {
            int total = 0;
            for (int d = 0; d < DAYS; d++) {
                total += profits[month][d][c];
            }
            if (total > maxProfit) {
                maxProfit = total;
                bestCommodity = commodities[c];
            }
        }
        return bestCommodity + " " + maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }
        int total = 0;
        for (int c = 0; c < COMMS; c++) {
            total += profits[month][day - 1][c];
        }
        return total;
    }

    public static int commodityProfitInRange(String commodity, int fromDay, int toDay) {
        int cIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(commodity)) {
                cIndex = c;
                break;
            }
        }

        if (cIndex == -1 || fromDay < 1 || toDay > DAYS || fromDay > toDay) {
            return -99999;
        }

        int total = 0;
        for (int m = 0; m < MONTHS; m++) {
            for (int d = fromDay - 1; d < toDay; d++) {
                total += profits[m][d][cIndex];
            }
        }
        return total;
    }


    public static int bestDayOfMonth(int month) { return (month < 0 || month >= MONTHS) ? -1 : 1234; }
    public static String bestMonthForCommodity(String comm) { return "DUMMY"; }
    public static int consecutiveLossDays(String comm) { return 1234; }
    public static int daysAboveThreshold(String comm, int threshold) { return 1234; }
    public static int biggestDailySwing(int month) { return (month < 0 || month >= MONTHS) ? -99999 : 1234; }
    public static String compareTwoCommodities(String c1, String c2) { return "DUMMY is better by 1234"; }
    public static String bestWeekOfMonth(int month) { return (month < 0 || month >= MONTHS) ? "INVALID_MONTH" : "DUMMY"; }

    public static void main(String[] args) {
        loadData();

    }
}
 