import com.google.gson.Gson;

public class ParseResponse {

    /**
     * this function parses the json format AI response
     * @param geminiResponse is the response which is being parsed by identifyTraitsAndUserDetails
     */
    ResponseStoredAfterParsing identifyTraitsAndUserDetails(String geminiResponse){
        Gson gson = new Gson();
        ResponseStoredAfterParsing responseStoredAfterParsing = gson.fromJson(geminiResponse,ResponseStoredAfterParsing.class);
        System.out.println(responseStoredAfterParsing.candidates.get(0).content.parts.get(0).text);
        return responseStoredAfterParsing;

    }
}
