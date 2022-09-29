import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Rainfall {
    private String cityName;
    Rainfall(String filename) {
        if (!filename.equals("")) {
            ArrayList<MonthlyRainfall> rainfallArr = readFile(filename);
            writeFile(rainfallArr);
        }
    }

    private ArrayList<MonthlyRainfall> readFile(String filename) {
        FileInputStream file = null;
        ArrayList<MonthlyRainfall> rainfall = new ArrayList<MonthlyRainfall>();
        try {
            file = new FileInputStream(filename);
            Scanner sc = new Scanner(file);
            cityName = sc.nextLine();
            String line = null;
            while (sc.hasNext()) {
                line = sc.nextLine();
                String[] splited = line.split("[ \t]");
                rainfall.add(new MonthlyRainfall(splited[0], Integer.parseInt(splited[1]), Double.parseDouble(splited[2])));
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rainfall;
    }

    private void writeFile(ArrayList<MonthlyRainfall> rainfallList) {
        try {
            FileOutputStream file = new FileOutputStream("RainfallStats.txt");
            PrintWriter write = new PrintWriter(file);
            int arrSize = rainfallList.size();
            AverageRainfall[] avgRainfall = new AverageRainfall[12];
            for (int i = 0; i < avgRainfall.length; i++) {
                avgRainfall[i] = new AverageRainfall();
            }
            for (int i = 0; i < arrSize; i++) {
                if (rainfallList.get(i).Month.toLowerCase().equals("january")) {
                    avgRainfall[0].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[0].Count;
                    avgRainfall[0].Month = "January";
                } else if (rainfallList.get(i).Month.toLowerCase().equals("february")) {
                    avgRainfall[1].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[1].Count;
                    avgRainfall[1].Month = "February";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("march")) {
                    avgRainfall[2].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[2].Count;
                    avgRainfall[2].Month = "March";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("april")) {
                    avgRainfall[3].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[3].Count;
                    avgRainfall[3].Month = "April";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("may")) {
                    avgRainfall[4].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[4].Count;
                    avgRainfall[4].Month = "May";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("june")) {
                    avgRainfall[5].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[5].Count;
                    avgRainfall[5].Month = "June";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("july")) {
                    avgRainfall[6].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[6].Count;
                    avgRainfall[6].Month = "July";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("august")) {
                    avgRainfall[7].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[7].Count;
                    avgRainfall[7].Month = "August";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("september")) {
                    avgRainfall[8].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[8].Count;
                    avgRainfall[8].Month = "September";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("october")) {
                    avgRainfall[9].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[9].Count;
                    avgRainfall[9].Month = "October";
                }
                else if (rainfallList.get(i).Month.toLowerCase().equals("november")) {
                    avgRainfall[10].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[10].Count;
                    avgRainfall[10].Month = "November";
                }
                else {
                    avgRainfall[11].TotalRain += rainfallList.get(i).Rainfall;
                    ++avgRainfall[11].Count;
                    avgRainfall[11].Month = "December";
                }
            }
            write.println(cityName);
            for (int i = 0; i < 12; i++) {
                String line = "The average rainfall amount for " + avgRainfall[i].Month + " is " + avgRainfall[i].getAverageRain();
                write.println(line);
            }
            write.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
