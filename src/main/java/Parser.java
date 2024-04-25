import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public void setUp() throws IOException {
        File input = new File("src/Resources/country-list.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements countryElements = doc.select("span.country-capital");
        Elements populationElements = doc.select("span.country-population");
        Elements areaElements = doc.select("span.country-area");

        for (int i = 0; i < countryElements.size(); i++) {
            String name = countryElements.get(i).text();
            String capital = countryElements.get(i).attr("title"); // Assuming capital is stored as title attribute
            int population = Integer.parseInt(populationElements.get(i).text().replaceAll("[^\\d]", ""));
            double area = Double.parseDouble(areaElements.get(i).text().replaceAll("[^\\d.]", ""));

            countries.add(new Country(name, capital, population, area));
        }
    }

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        sortedByName.sort(Comparator.comparing(Country::getName));
        return sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        sortedByPopulation.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        sortedByArea.sort(Comparator.comparingDouble(Country::getArea).reversed());
        return sortedByArea;
    }
}
