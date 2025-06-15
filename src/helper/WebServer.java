package helper;

import com.sun.net.httpserver.HttpServer;
import managers.UIManager;
import types.UIInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class WebServer {
    public static void start(UIManager uiManager) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        uiManager.setServer(server);

        server.createContext("/submit", exchange -> {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                        .lines().reduce("", (acc, line) -> acc + line);

                System.out.println("server side {\nRaw input: " + body);

                // Parse input
                UIInput parsedInput = parseInput(body);

                System.out.println("Code: " + parsedInput.inputCode);
                System.out.println("Params: " + parsedInput.parameters);
                System.out.println("}\n");

                // Send it to UI manager (send as necessary, e.g. raw string, or structured)
                uiManager.receiveInput(parsedInput); // or pass parsedInput

                // Respond
                String response = "Input received";
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1);
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }

    // Parsing helper
    public static UIInput parseInput(String input) {
        String[] parts = input.split(",", 2);
        String inputCode = parts[0].trim();
        Map<String, String> parameters = new HashMap<>();

        if (parts.length > 1) {
            String[] paramPairs = parts[1].split(",");
            for (String pair : paramPairs) {
                String[] kv = pair.split(":", 2);
                if (kv.length == 2) {
                    parameters.put(kv[0].trim(), kv[1].trim());
                }
            }
        }

        return new UIInput(inputCode, parameters);
    }
}