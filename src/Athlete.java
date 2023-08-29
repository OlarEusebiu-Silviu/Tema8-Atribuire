import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Athlete implements Comparable<Athlete> {
    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private String skiTimeResult;
    private List<ShootingResult[]> shootingResults;

    public Athlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult, String... shootingResults) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.shootingResults = Arrays.stream(shootingResults)
                .map(this::parseShootingResult)
                .collect(Collectors.toList());
    }

    private ShootingResult[] parseShootingResult(String shootingResult) {
        return shootingResult.chars()
                .mapToObj(c -> ShootingResult.valueOf(Character.toString((char) c).toUpperCase()))
                .toArray(ShootingResult[]::new);
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public List<ShootingResult[]> getShootingResults() {
        return shootingResults;
    }

    public int calculatePenalty() {
        int penalty = 0;
        for (ShootingResult[] shootingResult : shootingResults) {
            penalty += Arrays.stream(shootingResult).filter(result -> result == ShootingResult.MISS).count();
        }
        return penalty * 10;
    }

    public String getTotalTime() {
        int penalty = calculatePenalty();
        String[] timeParts = skiTimeResult.split(":");
        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);
        int totalTimeInSeconds = minutes * 60 + seconds + penalty;
        int finalMinutes = totalTimeInSeconds / 60;
        int finalSeconds = totalTimeInSeconds % 60;
        return String.format("%02d:%02d", finalMinutes, finalSeconds);
    }

    @Override
    public int compareTo(Athlete other) {
        return getTotalTime().compareTo(other.getTotalTime());
    }
}