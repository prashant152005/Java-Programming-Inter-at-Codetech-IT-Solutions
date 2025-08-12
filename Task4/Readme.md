# Recommendation System in Java

This project implements a simple user-based recommendation system using Java. It reads a CSV file containing user-item ratings, calculates cosine similarity between users, and recommends new items based on similar user preferences.

## Features

- Load user-item ratings from a CSV file.
- Compute cosine similarity between users.
- Recommend unrated items for a user based on most similar users.
- Console input for interactive testing.

## Usage

1. Prepare a CSV file in the following format:

    ```csv
    user,item,rating
    Alice,Item1,4
    Bob,Item1,5
    Alice,Item2,3
    Bob,Item3,4
    ```

2. Update the path to the CSV file in the Java code if necessary.
3. Compile and run the Java file:

    ```bash
    javac RecommendationSystem.java
    java RecommendationSystem
    ```

4. Enter the user name when prompted to receive recommendations.

## Example

If `Alice` and `Bob` have overlapping ratings and Bob rated an item that Alice hasn't, that item may be recommended to Alice if the similarity is high.

## Requirements

- Java 8 or above

## Files

- `RecommendationSystem.java` – Main Java program.
- `sample_data.csv` – Example dataset (place in the correct path as required).
