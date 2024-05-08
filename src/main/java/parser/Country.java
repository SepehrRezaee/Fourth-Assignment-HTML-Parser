import java.util.Objects;

public class Country {
    private String name;
    private String capital;
    private int population;
    private double area;

    /**
     * Constructor to initialize Country object.
     *
     * @param name      the name of the country
     * @param capital   the capital city of the country
     * @param population the population of the country
     * @param area      the area of the country in square kilometers
     */
    public Country(String name, String capital, int population, double area) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
    }

    // Getter for the name of the country
    public String getName() {
        return name;
    }

    // Getter for the capital city of the country
    public String getCapital() {
        return capital;
    }

    // Getter for the population of the country
    public int getPopulation() {
        return population;
    }

    // Getter for the area of the country in square kilometers
    public double getArea() {
        return area;
    }

    /**
     * Returns a string representation of the country object.
     */
    @Override
    public String toString() {
        return "Country{" +
               "name='" + name + '\'' +
               ", capital='" + capital + '\'' +
               ", population=" + population +
               ", area=" + area + " km²" +
               '}';
    }

    /**
     * Checks if this country is equal to another object.
     * 
     * @param o the object to compare against
     * @return true if both objects are countries with the same properties
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population &&
               Double.compare(country.area, area) == 0 &&
               Objects.equals(name, country.name) &&
               Objects.equals(capital, country.capital);
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, capital, population, area);
    }
}