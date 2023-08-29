import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        String csvData =
                "11, Umar Jorgson, SK, 30:27, xxoxx, xxxxx, xxoxx\n" +
                        "1, Jimmy Smiles, Marea Britanie, 29:15, xxoox, xooxo, xxxxx\n" +
                        "27, Piotr Smitzer, CZ, 30:10, xxxxx, xxxxx, xxxxx";


        List<Athlete> athletes = new ArrayList<>();

        String[] lines = csvData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            int athleteNumber = Integer.parseInt(parts[0].trim());
            String athleteName = parts[1].trim();
            String countryCode = parts[2].trim();
            String skiTimeResult = parts[3].trim();
            String[] shootingResults = Arrays.copyOfRange(parts, 4, parts.length);

            athletes.add(new Athlete(athleteNumber, athleteName, countryCode, skiTimeResult, shootingResults));
        }

        Collections.sort(athletes);

        System.out.println("Câștigător - " + athletes.get(0).getAthleteName() + " " + athletes.get(0).getTotalTime());
        System.out.println("Al doilea loc - " + athletes.get(1).getAthleteName() + " " + athletes.get(1).getTotalTime());
        System.out.println("Locul al treilea - " + athletes.get(2).getAthleteName() + " " + athletes.get(2).getTotalTime());
    }
}
