import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class StoreProfile {

    void profileStore(String geminiResponseOfPrompt){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd-MM-yy_H-m_");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateTime = time.format(dateTimeFormatter1);
        File storeProfile = new File(dateTime+"userProfile.txt");
        try {
            storeProfile.createNewFile();
        }
        catch (Exception e){
            System.out.println("[StoreProfile] Error creating user's profile");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        try{
            FileWriter fileWriter = new FileWriter(dateTime+"userProfile.txt");
            fileWriter.write(geminiResponseOfPrompt);
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("[StoreProfile] error writing in user's profile");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
