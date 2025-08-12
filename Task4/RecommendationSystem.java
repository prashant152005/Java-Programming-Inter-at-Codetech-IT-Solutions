import java.io.*;
import java.util.*;

public class RecommendationSystem {

    // Map to hold user -> (item -> rating)
    private static final Map<String, Map<String, Integer>> userRatings = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "Task4_RecommendationSystem/sample_data.csv";

        try {
            loadRatings(filePath);
            System.out.print("Enter user name for recommendations: ");
            String user = scanner.nextLine().trim();

            List<String> recommendations = getRecommendations(user);
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations available for user: " + user);
            } else {
                System.out.println("Recommended items for " + user + ": " + recommendations);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        } finally {
            scanner.close();
        }
    }

    // Load user-item ratings from CSV file
    private static void loadRatings(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String user = parts[0].trim();
                String item = parts[1].trim();
                int rating;
                try {
                    rating = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                userRatings.putIfAbsent(user, new HashMap<>());
                userRatings.get(user).put(item, rating);
            }
        }
    }

    // Recommend items for the given user based on cosine similarity
    private static List<String> getRecommendations(String user) {
        Map<String, Integer> targetUserRatings = userRatings.get(user);
        if (targetUserRatings == null) {
            return Collections.emptyList();
        }

        Map<String, Double> recommendationScores = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : userRatings.entrySet()) {
            String otherUser = entry.getKey();
            if (otherUser.equals(user)) continue;

            double similarity = computeCosineSimilarity(targetUserRatings, entry.getValue());
            if (similarity <= 0) continue;

            for (Map.Entry<String, Integer> itemEntry : entry.getValue().entrySet()) {
                String item = itemEntry.getKey();
                if (!targetUserRatings.containsKey(item)) {
                    recommendationScores.put(item,
                        recommendationScores.getOrDefault(item, 0.0) + similarity);
                }
            }
        }

        List<String> sortedRecommendations = new ArrayList<>(recommendationScores.keySet());
        sortedRecommendations.sort((item1, item2) ->
            Double.compare(recommendationScores.get(item2), recommendationScores.get(item1)));

        return sortedRecommendations;
    }

    // Compute cosine similarity between two users' ratings
    private static double computeCosineSimilarity(Map<String, Integer> a, Map<String, Integer> b) {
        Set<String> commonItems = new HashSet<>(a.keySet());
        commonItems.retainAll(b.keySet());
        if (commonItems.isEmpty()) return 0.0;

        double dotProduct = 0.0, normA = 0.0, normB = 0.0;

        for (String item : commonItems) {
            dotProduct += a.get(item) * b.get(item);
        }

        for (int rating : a.values()) {
            normA += rating * rating;
        }

        for (int rating : b.values()) {
            normB += rating * rating;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
