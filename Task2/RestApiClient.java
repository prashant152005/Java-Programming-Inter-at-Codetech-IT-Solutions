/*
================================================================================
 README: Java REST API Client (Random User + Weather API)
================================================================================
Author : Prashant Kumar Pathak 

📦 PURPOSE:
This Java program fetches:
1. Random user data from https://randomuser.me/api/
2. Weather data from https://api.weatherapi.com/

================================================================================
🔧 REQUIREMENTS:
- Java 8 or higher
- Internet connection
- JSON library (org.json)

================================================================================
📥 DOWNLOAD JAR LIBRARY:
Download json library from:
https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar

Save the file in the same directory as this Java file.

================================================================================
🧾 FILE STRUCTURE:
Your directory should look like:
.
├── RestApiClient.java
└── json-20210307.jar

================================================================================
🧪 HOW TO COMPILE:

🔹 Windows:
javac -cp .;json-20210307.jar RestApiClient.java

🔹 macOS/Linux:
javac -cp .:json-20210307.jar RestApiClient.java

================================================================================
▶️ HOW TO RUN:

🔹 Windows:
java -cp .;json-20210307.jar RestApiClient

🔹 macOS/Linux:
java -cp .:json-20210307.jar RestApiClient

================================================================================
📦 HOW TO CREATE A SINGLE EXECUTABLE JAR:

🔹 Windows:
jar --create --file RestApiClient.jar --main-class=RestApiClient -C . RestApiClient.class json-20210307.jar

🔹 Run the JAR:
java -cp RestApiClient.jar;json-20210307.jar RestApiClient

================================================================================
🔑 Replace the below `YOUR_API_KEY` with API key from https://www.weatherapi.com/

================================================================================
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) {
        fetchRandomUser();
        fetchWeather("Mumbai"); // Change city as needed
    }

    // Fetch random user data
    public static void fetchRandomUser() {
        try {
            URL url = new URL("https://randomuser.me/api/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int code = conn.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonText.append(line);
                }
                reader.close();

                JSONObject response = new JSONObject(jsonText.toString());
                JSONArray results = response.getJSONArray("results");
                JSONObject user = results.getJSONObject(0);

                String name = user.getJSONObject("name").getString("first") + " " +
                              user.getJSONObject("name").getString("last");
                String email = user.getString("email");
                String country = user.getJSONObject("location").getString("country");

                System.out.println("---- Random User Info ----");
                System.out.println("Name    : " + name);
                System.out.println("Email   : " + email);
                System.out.println("Country : " + country);
                System.out.println();
            } else {
                System.out.println("Failed to fetch user. HTTP Code: " + code);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching user: " + e.getMessage());
        }
    }

    // Fetch weather data using WeatherAPI
    public static void fetchWeather(String city) {
        try {
            String apiKey = "YOUR_API_KEY"; // ← Replace this with API key from https://www.weatherapi.com/
            String endpoint = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int code = conn.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonText.append(line);
                }
                reader.close();

                JSONObject response = new JSONObject(jsonText.toString());
                JSONObject location = response.getJSONObject("location");
                JSONObject current = response.getJSONObject("current");
                JSONObject condition = current.getJSONObject("condition");

                System.out.println("---- Weather Info ----");
                System.out.println("City       : " + location.getString("name"));
                System.out.println("Country    : " + location.getString("country"));
                System.out.println("Temp (°C)  : " + current.getDouble("temp_c"));
                System.out.println("Condition  : " + condition.getString("text"));
                System.out.println("Humidity   : " + current.getInt("humidity") + "%");
                System.out.println("Wind (kph) : " + current.getDouble("wind_kph"));
            } else {
                System.out.println("Failed to fetch weather. HTTP Code: " + code);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching weather: " + e.getMessage());
        }
    }
}

