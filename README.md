# Rock Paper Scissors Overkill

A classic console-based "Rock, Paper, Scissors" game written in Java. This project demonstrates clean architecture, SOLID principles, and modular design.

## Features

- Interactive command-line gameplay against a Bot.
- Cleanly separated architecture (Core logic, IO processing, and Game control).
- Built-in scoreboard tracking across multiple rounds.
- Robust input validation and error handling.

## Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Apache Maven** 3.6.0 or higher

## Project Structure

- `digital.yoanthehuman.rps.core` - Contains the domain logic (`Move`, `RoundEvaluator`, `ScoreBoard`, `BotMoveGenerator`).
- `digital.yoanthehuman.rps.io` - Handles user input parsing and standard output (`InputReader`, `OutputWriter`, `MoveParser`).
- `digital.yoanthehuman.rps.game` - Coordinates the game flow (`GameController`).
- `digital.yoanthehuman.rps.Main` - The application entry point that wires all dependencies together using dependency injection.

## Getting Started

### Running in GitHub Codespaces

This repository is fully configured to work in GitHub Codespaces. This is the fastest way to start without installing anything on your local machine!

1. Click the **Code** button on the repository page in GitHub.
2. Select the **Codespaces** tab and click **Create codespace on main**.
3. Wait for the environment to build (it comes pre-configured with Java 17 and Maven).
4. Once the editor loads, open the integrated terminal (`Ctrl + \``) and run the game directly:
   ```bash
   mvn clean install
   mvn exec:java
   ```

### Building the Project Locally

To compile the application and run unit tests on your machine, navigate to the project root directory and execute:

```bash
mvn clean install
```

### Running the Game

You can run the application directly from your terminal using the Maven Exec plugin:

```bash
mvn exec:java
```

### Running Tests

To execute the JUnit 5 test suite:

```bash
mvn test
```

## Gameplay Instructions

1. Upon starting the game, you will be prompted to enter your move.
2. Type `Rock`, `Paper`, or `Scissors` (case-insensitive).
3. The bot will automatically generate its move.
4. The result of the round (Win, Loss, or Tie) will be displayed along with the current scoreboard.
5. You will be asked if you want to play another round (`yes`/`no`).
