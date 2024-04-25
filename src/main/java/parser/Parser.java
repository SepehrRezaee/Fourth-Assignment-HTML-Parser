import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parser {
    private List<Country> countries;

    public Parser() {
        this.countries = new ArrayList<>();
    }

    /**
     * Parses the HTML file to extract country data.
     * 
     * @param filePath Path to the HTML file containing country data.
     * @throws IOException If an input or output exception occurred
     */
    public void parseHtmlFile(String filePath) throws IOException {
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements countryElements = doc.select("span.country-capital");
        Elements populationElements = doc.select("span.country-population");
        Elements areaElements = doc.select("span.country-area");

        for (int i = 0; i < countryElements.size(); i++) {
            String name = countryElements.get(i).text();
            String capital = countryElements.get(i).attr("title"); // Assuming capital is stored as title attribute
            int population = parseInteger(populationElements.get(i).text());
            double area = parseDouble(areaElements.get(i).text());

            countries.add(new Country(name, capital, population, area));
        }
    }

    // Utility method to parse integers safely
    private int parseInteger(String data) {
        try {
            return Integer.parseInt(data.replaceAll("[^\\d]", ""));
        } catch (NumberFormatException e) {
            return 0; // Default to 0 if parsing fails
        }
    }

    // Utility method to parse doubles safely
    private double parseDouble(String data) {
        try {
            return Double.parseDouble(data.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0.0 if parsing fails
        }
    }

    // Sorts countries by name
    public List<Country> sortByName() {
        countries.sort(Comparator.comparing(Country::getName));
        return new ArrayList<>(countries);
    }

    // Sorts countries by population in descending order
    public List<Country> sortByPopulation() {
        countries.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        return new ArrayList<>(countries);
    }

    // Sorts countries by area in descending order
    public List<Country> sortByArea() {
        countries.sort(Comparator.comparingDouble(Country::getArea).reversed());
        return new ArrayList<>(countries);
    }
}