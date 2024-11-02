package servlet;

import com.google.gson.Gson; // Add this import for JSON conversion
import DAO.DAOConcert; // Ensure you import your DAO class
import pojo.Concert; // Ensure you import your Concert model class
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EventServlet", urlPatterns = {"/EventServlet"})
public class EventServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        DAOConcert daoConcert = new DAOConcert();
        List<Concert> concertList = daoConcert.retrieveTblConcert(); 

        try (PrintWriter out = response.getWriter()) {
            // Create a StringBuilder to construct the JSON response
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("[");

            for (int i = 0; i < concertList.size(); i++) {
                Concert concert = concertList.get(i);
                jsonBuilder.append("{");
                jsonBuilder.append("\"id\":").append(concert.getId()).append(",");
                jsonBuilder.append("\"name\":\"").append(concert.getName()).append("\",");
                jsonBuilder.append("\"desc\":\"").append(concert.getDesc()).append("\",");
                jsonBuilder.append("\"dateOfConcert\":\"").append(concert.getDateOfConcert()).append("\",");
                if (concert.getConcertView() != null) {
                    jsonBuilder.append("\"concertView\":{");
                    jsonBuilder.append("\"image\":\"").append(concert.getConcertView().getImage()).append("\"");
                    jsonBuilder.append("}");
                } else {
                    jsonBuilder.append("\"concertView\":{");
                    jsonBuilder.append("\"image\":\"\""); // or placeholder
                    jsonBuilder.append("}");
                }
                jsonBuilder.append("}");

                // Add a comma if it's not the last element
                if (i < concertList.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");

            // Print the JSON response
            out.print(jsonBuilder.toString());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Event Servlet for retrieving concert data";
    }
}
