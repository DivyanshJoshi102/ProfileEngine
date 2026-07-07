# 🦸 ProfileEngine

A Java CLI application that analyzes a user's personality using **Google Gemini AI** and generates a unique superhero alter ego based on their responses.

The application asks a series of personality questions, sends the responses to Gemini, and receives a structured JSON analysis containing personality traits, strengths, weaknesses, and a custom superhero profile.

---

## ✨ Features

- Interactive command-line questionnaire
- Collects user information (name & gender)
- Reads questions from an external text file
- Generates an AI prompt dynamically
- Connects to Google Gemini API
- Parses JSON responses using Gson
- Produces:
    - Personality traits
    - Strengths
    - Weaknesses
    - Personality archetype
    - Custom superhero
        - Name
        - Power
        - Description

---

## 🛠️ Tech Stack

- Java 21
- Maven
- Google Gemini API
- Gson

---

## 📂 Project Structure

```
src
├── Main.java
├── User.java
├── Quiz.java
├── QuestionAnswerPair.java
├── PromptEngine.java
├── GeminiClient.java
├── ParseResponse.java
├── ResponseStoredAfterParsing.java
├── Candidate.java
├── Content.java
├── Part.java
└── questions.txt
```

---

## ⚙️ How It Works

1. User enters their name and gender.
2. The application loads questions from `questions.txt`.
3. The user answers each question.
4. A prompt is generated using all responses.
5. The prompt is sent to Google Gemini AI.
6. Gemini returns a JSON response.
7. Gson parses the JSON into Java objects.
8. The generated superhero profile is displayed.

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven
- A Google Gemini API key

### Clone the repository

```bash
git clone https://github.com/yourusername/superhero-analyzer.git
cd superhero-analyzer
```

### Set the API key

Create an environment variable named:

```
GEMINI_API_KEY
```

Example:

**Windows PowerShell**

```powershell
$env:GEMINI_API_KEY="YOUR_API_KEY"
```

**Windows (Permanent)**

```powershell
setx GEMINI_API_KEY "YOUR_API_KEY"
```

Restart your terminal after using `setx`.

---

## ▶️ Run the project

Compile:

```bash
mvn compile
```

Run:

```bash
mvn exec:java
```

Or simply run `Main.java` from your IDE.

---

## 📝 Example Questions

Some of the personality questions include:

- What would you do if you woke up with ₹100 crore?
- Which superpower would you choose?
- Would you accept immortality?
- Smartest person or luckiest person?
- What fictional universe would you survive in?

---

## 📦 Example Output

The AI returns structured JSON similar to:

```json
{
  "traits": [
    "Strategic",
    "Curious",
    "Resilient"
  ],
  "strengths": [
    "Leadership",
    "Creativity",
    "Problem Solving"
  ],
  "weaknesses": [
    "Overthinking",
    "Impatience"
  ],
  "archetype": "Visionary",
  "superhero": {
    "name": "Night Sentinel",
    "power": "Reality Manipulation",
    "description": "A calm strategist who protects society by predicting threats before they occur."
  }
}
```

---

## 📚 Libraries Used

| Library | Purpose |
|----------|---------|
| Gson | JSON parsing |
| Java HttpClient | API communication |
| Maven | Dependency management |

---



## 👨‍💻 Author

Developed by **Divyansh Joshi**

---

