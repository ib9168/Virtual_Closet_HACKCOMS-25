package clothes.ai;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private TextField garmentInput;
    @FXML private TextArea resultArea;

    private static final String BACKEND_URL = "http://localhost:8000";

    @FXML
    private void handleFindIdeas() {
        String text = garmentInput.getText();
        if (text == null || text.isBlank()) {
            resultArea.setText("Please enter a clothing description first.");
            return;
        }

        resultArea.setText("Finding outfit ideas...");
        callBackend("/api/outfits/find-ideas", text);
    }

    @FXML
    private void handleOpenLogin() {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            // show a brief message in the result area if navigation fails
            resultArea.setText("Error opening login page: " + e.getMessage());
        }
    }

    @FXML
    private void handleOpenCloset() {
        try {
            App.setRoot("closet");
        } catch (IOException e) {
            resultArea.setText("Error opening closet page: " + e.getMessage());
        }
    }

    private void callBackend(String endpoint, String text) {
        CompletableFuture.runAsync(() -> {
            try {
                URL url = URI.create(BACKEND_URL + endpoint).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Create request JSON
                String json = buildRequestJson(text);

                // Send request
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                }

                // Read response
                String response;
                try (Scanner s = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8).useDelimiter("\\A")) {
                    response = s.hasNext() ? s.next() : "";
                }

                // Extract the idea text from response JSON
                String result = extractIdea(response);

                final String displayText = result.isEmpty() ?
                    "Error: Unexpected response format" : result;

                Platform.runLater(() -> resultArea.setText(displayText));

            } catch (Exception e) {
                Platform.runLater(() -> resultArea.setText(
                    "Error connecting to backend:\n" + e.getMessage() + 
                    "\n\nMake sure the backend is running at " + BACKEND_URL));
            }
        });
    }

    static String buildRequestJson(String text) {
        JsonObject body = new JsonObject();
        body.addProperty("userId", "user");
        body.addProperty("text", text);
        return body.toString();
    }

    // Returns the idea text from the response JSON, or "" if it is missing or invalid
    static String extractIdea(String response) {
        try {
            JsonElement idea = JsonParser.parseString(response).getAsJsonObject().get("idea");
            return idea == null || idea.isJsonNull() ? "" : stripMarkdown(idea.getAsString());
        } catch (JsonParseException | IllegalStateException e) {
            return "";
        }
    }

    // The result area shows plain text, so remove the Markdown symbols Gemini includes
    static String stripMarkdown(String text) {
        return text
            .replaceAll("(?m)^#{1,6}\\s*", "")          // Headings
            .replace("**", "")                          // Bold
            .replaceAll("(?m)^(\\s*)\\*\\s+", "$1- ")   // Bullet points
            .replaceAll("\\*(\\S(?:.*?\\S)?)\\*", "$1"); // Italics
    }
}
