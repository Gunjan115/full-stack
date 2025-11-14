import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

public class WeatherServer {

    public static void main(String[] args) throws Exception {
        // ✅ Run server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("🌤️ Weather server started on http://localhost:8080");

        // ✅ Weather API endpoint
        server.createContext("/weather", new WeatherHandler());

        // ✅ Default homepage (avoids 404 errors)
        server.createContext("/", exchange -> {
            String msg = """
                    <html>
                      <head><title>Weather Server</title></head>
                      <body style="font-family: Arial; text-align: center; margin-top: 60px;">
                        <h2>🌤️ Weather Server is running!</h2>
                        <p>Try accessing:
                           <a href='/weather?city=Delhi'>/weather?city=Delhi</a>
                        </p>
                      </body>
                    </html>
                    """;
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, msg.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(msg.getBytes());
            }
        });

        server.setExecutor(null); // use default thread pool
        server.start();
    }

    // =============================
    // WeatherHandler class
    // =============================
    static class WeatherHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();

            // ✅ Allow frontend requests (CORS)
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            // Validate query
            if (query == null || !query.startsWith("city=")) {
                sendResponse(exchange, 400, "{\"error\":\"Please provide ?city=CityName\"}");
                return;
            }

            String city = query.split("=")[1];
            try {
                // Get data from WeatherService (you must have this class)
                List<WeatherService.DailyWeatherData> forecast = WeatherService.getFiveDayForecast(city);

                StringBuilder jsonBuilder = new StringBuilder("[");
                for (int i = 0; i < forecast.size(); i++) {
                    jsonBuilder.append(forecast.get(i).toJson());
                    if (i != forecast.size() - 1) jsonBuilder.append(",");
                }
                jsonBuilder.append("]");

                sendResponse(exchange, 200, jsonBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "{\"error\":\"Failed to fetch weather data\"}");
            }
        }

        private void sendResponse(HttpExchange exchange, int code, String response) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(code, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
