import java.io.IOException;

/**
 * this class calls the user class
 * creates object of Quiz class and calls its functions
 * creates object of PromptEngine class and calls its functions
 */
public class Main {

    /**
     * main function
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException{
        User user = new User();
        user.inputName();
        user.inputGender();
        Quiz quiz = new Quiz();
        quiz.setQuestions();
        quiz.inputAnswers();
        PromptEngine promptEngine = new PromptEngine();
        String prompt = promptEngine.promptGenerator(user,quiz); // stores the prompt sent to ai


        //geminiclient is sending prompt to ai
        final String apiKey = System.getenv("GEMINI_API_KEY");// api key to access gemini ai model
        if(apiKey == null){
            System.out.println("Gemini API Key not set");
        }
        else {

            GeminiClient geminiClient = new GeminiClient(apiKey);
            String promptResponse = geminiClient.sendPrompt(prompt); // stores the ai response to prompt
            ParseResponse parseResponse = new ParseResponse();
            parseResponse.identifyTraitsAndUserDetails(promptResponse);

        }
    }
}
