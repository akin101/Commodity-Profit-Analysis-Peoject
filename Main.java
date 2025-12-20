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


    public static int bestDayOfMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        int maxProfit = Integer.MIN_VALUE;
        int bestDay = 1;

        for (int d = 0; d < DAYS; d++) {
            int dailyTotal = 0;

            for (int c = 0; c < COMMS; c++) {
                dailyTotal += profits[month][d][c];
            }

            if (dailyTotal > maxProfit) {
                maxProfit = dailyTotal;
                bestDay = d + 1;
            }
        }

        return bestDay;
    }
    public static String bestMonthForCommodity(String comm) {
        if (comm == null) {
            return "INVALID_COMMODITY";
        }

        comm = comm.trim();
        int cIndex = -1;
        for(int c = 0; c<COMMS ; c++){
            if(commodities[c].equals(comm)){
                cIndex = c;
                break;
            }
        }
        if(cIndex == -1){
            return "INVALID_COMMODITY";
        }
        int maxProfit = Integer.MIN_VALUE;
        int bestMonthIndex = 0;
        for(int m = 0; m < MONTHS; m++){
            int monthlyTotal = 0;
            for(int d=0; d<DAYS ; d++){
                monthlyTotal += profits[m][d][cIndex];
            }
            if(monthlyTotal > maxProfit){
                maxProfit = monthlyTotal;
                bestMonthIndex = m;
            }
        }
        return months[bestMonthIndex];
    }
    public static int consecutiveLossDays(String comm) {
            if (comm == null) {
                return -1;
            }

            comm = comm.trim();

            int cIndex = -1;
            for (int c = 0; c < COMMS; c++) {
                if (commodities[c].equals(comm)) {
                    cIndex = c;
                    break;
                }
            }

            if (cIndex == -1) {
                return -1;
            }

            int maxStreak = 0;
            int currentStreak = 0;

            for (int m = 0; m < MONTHS; m++) {
                for (int d = 0; d < DAYS; d++) {
                    if (profits[m][d][cIndex] < 0) {
                        currentStreak++;
                        if (currentStreak > maxStreak) {
                            maxStreak = currentStreak;
                        }
                    } else {
                        currentStreak = 0;
                    }
                }
            }
            return maxStreak;
    }

    public static int daysAboveThreshold(String comm, int threshold) {

        int cIndex = -1;

        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                cIndex = c;
                break;
            }
        }

        if (cIndex == -1) {
            return -1;
        }

        int count = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                if (profits[m][d][cIndex] > threshold) {
                    count++;
                }
            }
        }

        return count;
    }
    public static int biggestDailySwing(int month) {
        return 1234;
    }
    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }
    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();

    }
}
 