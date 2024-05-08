import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HockeyParser {
    private List<HockeyTeam> hockeyTeams = new ArrayList<>();

    /**
     * Parses the HTML content of a given URL to extract hockey team data.
     *
     * @param url URL of the webpage containing hockey team information.
     * @throws IOException If an input or output exception occurred.
     */
    public void parseHockeyTeams(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div.team");  // Selector for team containers.

        for (Element element : elements) {
            String teamName = element.select("h1.team-name").text();  // Selector for team name.
            String location = element.select("span.location").text();  // Selector for location (example).
            int foundedYear = parseInt(element.select("span.founded-year").text());  // Selector for founded year (example).

            hockeyTeams.add(new HockeyTeam(teamName, location, foundedYear));
        }
    }

    /**
     * Attempts to parse an integer from a string, defaulting to 0 if parsing fails.
     *
     * @param data String potentially containing integer data.
     * @return The integer value parsed from the string, or 0 if parsing fails.
     */
    private int parseInt(String data) {
        try {
            return Integer.parseInt(data.replaceAll("[^\\d]", ""));
        } catch (NumberFormatException e) {
            return 0;  // Default to 0 if parsing fails.
        }
    }

    /**
     * Returns the list of hockey teams parsed from the website.
     *
     * @return A list of HockeyTeam objects.
     */
    public List<HockeyTeam> getHockeyTeams() {
        return new ArrayList<>(hockeyTeams);  // Returns a copy to avoid external modifications.
    }
}

/**
 * Class to represent a hockey team.
 */
class HockeyTeam {
    private String name;
    private String location;
    private int foundedYear;

    public HockeyTeam(String name, String location, int foundedYear) {
        this.name = name;
        this.location = location;
        this.foundedYear = foundedYear;
    }

    @Override
    public String toString() {
        return "HockeyTeam{" +
               "name='" + name + '\'' +
               ", location='" + location + '\'' +
               ", foundedYear=" + foundedYear +
               '}';
    }
}