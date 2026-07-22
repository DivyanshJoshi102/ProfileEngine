import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class StoreProfile {

    void profileStore(String geminiResponseOfPrompt){
        JSONObject profileJson = new JSONObject(geminiResponseOfPrompt);
        JSONObject superhero = profileJson.getJSONObject("superhero");
        String superheroName = superhero.getString("name");
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd-MM-yy_H-m_");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateTime = time.format(dateTimeFormatter1);
        File storeProfile = new File("data/UserProfile/" + dateTime + superheroName+".txt");
        try {
            storeProfile.createNewFile();
        }
        catch (Exception e){
            System.out.println("[StoreProfile] Error creating user's profile");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        try{
            FileWriter fileWriter = new FileWriter("data/UserProfile/" +dateTime+ superheroName+".txt");
            fileWriter.write(geminiResponseOfPrompt);
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("[StoreProfile] error writing in user's profile");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
