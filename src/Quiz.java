import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * this class stores question answer pair so that they can be easily accessed
 */
public class Quiz {

    ArrayList<QuestionAnswerPair> questionAnswerPairList = new ArrayList<>();

    void setQuestions() {
        try {
            File file = new File("src/questions.txt");
            Scanner sc = new Scanner(file);
            for (int i = 0; i < 10; i++) {
                QuestionAnswerPair questionAnswerPair = new QuestionAnswerPair();
                questionAnswerPair.question = sc.nextLine();
                questionAnswerPairList.add(questionAnswerPair);
            }
        }
        catch (Exception e){
            System.out.println("Could not find file");
            e.printStackTrace();
        }

    }

    void inputAnswers(){
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<questionAnswerPairList.size(); i++){
            System.out.println(questionAnswerPairList.get(i).question);
            questionAnswerPairList.get(i).answer=sc.nextLine();
        }
    }
}