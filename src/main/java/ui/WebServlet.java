import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/countries")
public class WebServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        // Fetch country data (assuming a method to fetch data)
        List<Country> countries = fetchCountries(); // This method needs to be implemented
        
        // Generate HTML content
        out.println("<html><body>");
        out.println("<h1>Country List</h1>");
        out.println("<ul>");
        for (Country country : countries) {
            out.println("<li>" + country + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    private List<Country> fetchCountries() {
        // Placeholder: Replace this with actual database fetch or other logic
        return List.of(new Country("USA", "Washington D.C.", 331000000, 9833520),
                       new Country("Canada", "Ottawa", 37742154, 9984670),
                       new Country("United Kingdom", "London", 67886011, 242495));
    }
}
