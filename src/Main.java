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
        ChoiceGivenToUser choiceGivenToUser = new ChoiceGivenToUser();
        choiceGivenToUser.userChoice();
    }
}
