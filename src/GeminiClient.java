import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** establishing https connection with AI
 * send prompt (request to AI) through the https client
 *  return -> json data (AI response to prompt)
 * json response parse and populate result class
 */
public class GeminiClient {

    private final String apiKey;
    private final HttpClient client;
    private final String url;

    GeminiClient(final String apiKey){
        this.apiKey= apiKey;
        client = HttpClient.newHttpClient();
        url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";}

    String sendPrompt(String prompt) throws IOException,  InterruptedException{

        String escapedPrompt = prompt
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        String sendJsonPrompt = """
        {
            "contents": [
                    {
                        "parts": [
                        {
                            "text": "%s"
                         }
                         ]
                    }
            ]
        }
        """.formatted(escapedPrompt);
        //System.out.println("[GeminiClient] Sending Prompt: " + sendJsonPrompt);


        /*
         *  create http request body
         *  print http request
         *  print AI response body
         *  print AI response status code
         */
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(sendJsonPrompt))
                .build();
        //System.out.println("[GeminiClient] HTTP Request: " + request);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("[GeminiClient] Response Body:" + response.body());//hello ka ai ka jawab
        //System.out.println("[GeminiClient] Response Status Code:" + response.statusCode()); //200
        return response.body();

    }
}