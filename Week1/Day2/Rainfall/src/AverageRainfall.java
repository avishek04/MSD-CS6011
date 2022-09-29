public class AverageRainfall {
    public String Month;
    public int Count;
    public double TotalRain;

    public double getAverageRain() {
        double avg = TotalRain/Count;
        return Math.round(avg * 100) / 100.0;
    }
}
