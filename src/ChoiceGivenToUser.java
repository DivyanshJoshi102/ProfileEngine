import java.util.Arrays;
import java.util.Scanner;

public class ChoiceGivenToUser {
    void userChoice() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to continue quiz, enter 2 to view past profiles: ");
        choice = scanner.nextInt();
        if (choice == 1 || choice == 2) {
            handleFlow(choice);
        } else {
            System.out.println("Did not enter valid option");
        }
    }

    void handleFlow(int userChoice) {
        switch (userChoice) {
            case 1 -> {
                takingQuiz();
            }
            case 2 -> {
                viewingPastProfile();
            }
        }
    }

    void takingQuiz() {
        User user = new User();
        user.inputName();
        user.inputGender();
        Quiz quiz = new Quiz();
        quiz.setQuestions();
        quiz.inputAnswers();
        PromptEngine promptEngine = new PromptEngine();
        String prompt = promptEngine.promptGenerator(user, quiz); // stores the prompt sent to ai
        creatingUserProfile(prompt);
    }
    void creatingUserProfile(String prompt){
        //geminiclient is sending prompt to ai
        final String apiKey = System.getenv("GEMINI_API_KEY");// api key to access gemini ai model
        if (apiKey == null) {
            System.out.println("Gemini API Key not set");
        } else {
            String promptResponse = "";
            GeminiClient geminiClient = new GeminiClient(apiKey);
            try {
                promptResponse = geminiClient.sendPrompt(prompt); // stores the ai response to prompt
            } catch (Exception e) {
                System.out.println("[ChoiceGivenToUser] exception:" + Arrays.toString(e.getStackTrace()));
            }
            ParseResponse parseResponse = new ParseResponse();
            ResponseStoredAfterParsing responseStoredAfterParsing = parseResponse.identifyTraitsAndUserDetails(promptResponse);
            StoreProfile storeProfile = new StoreProfile();
            storeProfile.profileStore(responseStoredAfterParsing.candidates.get(0).content.parts.get(0).text);
        }
    }
    void viewingPastProfile(){
        PastProfileListViewer pastProfileViewer = new PastProfileListViewer();
        pastProfileViewer.pastProfileList();
    }
}
