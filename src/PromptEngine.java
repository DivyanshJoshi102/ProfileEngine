/**
 *    this class will give the defined prompt to gemini ai
 *    returns the sent prompt to ai
 */
public class PromptEngine {

    String promptGenerator(User user, Quiz quiz){
        String basePrompt=
            """
                This user is a/an %s. I want to analyze their personality and character and want to know about it. from that i want to assign them a superhero name (like batman, superman, etc.) You are an expert personality analyst, storyteller, and character designer.
                Based on the user's answers below, analyze their personality and create a unique alter ego.
                Analyze the user's answers. Return ONLY valid JSON.
                Do not wrap the JSON in markdown.
                Do not add explanations.
                Do not add text before or after the JSON.,
                Expected format:
                {
                \"traits": [\"\",\"\",\"\",\"\",\"\"],
                \"strengths\": [\"\",\"\",\"\"],
                \"weaknesses\": [\"\",\"\",\"\"],
                \"archetype\": \"\",
                \"superhero\": {
                    \"name\": \"\",
                    \"power\": \"\",
                    \"description\": \"\"
                    }
                }
        """.formatted(user.gender);
        for(int i=0; i<quiz.questionAnswerPairList.size(); i++){
            basePrompt = basePrompt + quiz.questionAnswerPairList.get(i).question + quiz.questionAnswerPairList.get(i).answer+"    ";
        }
        return basePrompt;
    }
}