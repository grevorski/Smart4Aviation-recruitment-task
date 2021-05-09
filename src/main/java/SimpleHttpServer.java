import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/info", new InfoHandler());
        server.createContext("/flight", new GetFlightHandler());
        server.createContext("/flight/weight", new GetWeightHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The server is running");
    }


    private static final StringBuilder response = new StringBuilder();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    // http://localhost:8000/info
    private static class InfoHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = "Use /flight/weight?flightNumber=6043&departureDate=2015-03-18T10:37:13-01:00 /enter parameters in url to get cargo/baggage/total weight\n" +
            "Use /flight?IATA_Airport_Code=ANC&departureDate=2015-03-18T10:37:13-01:00 /enter parameters in url to get number of flights departing/arriving and total number of baggage arriving/departing\n";
            SimpleHttpServer.writeResponse(httpExchange, response);
        }
    }
    private static class GetFlightHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {

            FlightService flightService = new FlightService();
            Map <String,String>parms = SimpleHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());
            String code = null;
            response.append("<html><body>");
            if(parms.get("IATA_Airport_Code").equals("")) response.append("You did not pass IATA Airport Code").append("<br/>");
            else code = parms.get("IATA_Airport_Code");

            Date date = null;
            String dateInString = parms.get("departureDate");
            try {
                date = formatter.parse(dateInString);
            } catch (ParseException e) {
                response.append("Passed date is not in correct format").append("<br/>");
            }


            if(code != null && date !=null) {
                response.append("flightNumber : ").append(code).append("<br/>");
                response.append("departureDate : ").append(date).append("<br/>").append("<br/>");
                response.append("For Requested IATA Airport Code and date: ").append("<br/>");
                response.append(flightService.getFlights(code, date));
            }

            response.append("</body></html>");
            SimpleHttpServer.writeResponse(httpExchange, response.toString());
        }
    }

    private static class GetWeightHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {

            FlightService flightService = new FlightService();
            Map <String,String>parms = SimpleHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());

            Integer number = null;

            response.append("<html><body>");
            try{
                if(parms.get("flightNumber").equals("")) response.append("Passed number is null").append("<br/>");
                else number = Integer.valueOf(parms.get("flightNumber"));
            }catch (NumberFormatException e) {
                response.append("Input String cannot be parsed to Integer.").append("<br/>");
            }

            Date date = null;
            String dateInString = parms.get("departureDate");
            try {
                date = formatter.parse(dateInString);
            } catch (ParseException e) {
                response.append("Passed date is null").append("<br/>");
            }


            if( number != null && date !=null) {

                    response.append("flightNumber : ").append(number).append("<br/>");
                    response.append("departureDate : ").append(date).append("<br/>").append("<br/>");
                    response.append("For Requested Flight Number and date: ").append("<br/>");
                    response.append(flightService.getCargo(number, date));

                }
            response.append("</body></html>");
            SimpleHttpServer.writeResponse(httpExchange, response.toString());
        }
    }

    private  static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    /**
     * returns the url parameters in a map
     * @param query
     * @return map
     */
    private  static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

}