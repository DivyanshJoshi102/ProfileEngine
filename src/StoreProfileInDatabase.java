import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

public class StoreProfileInDatabase {

    private Connection connectingDB(){
        final String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        final String dbUsername = System.getenv("db_username");
        final String dbPassword = System.getenv("db_password");
        try{
            Connection connection = DriverManager.getConnection(
                    dbUrl,
                    dbUsername,
                    dbPassword
            );
            return connection;
        }
        catch(Exception e){
            System.out.println("[StoreProfileInDatabase] error while making connection with database");
            e.printStackTrace();
        }
        return null;
    }

    void insertingDataInDb(String geminiPromptResponse, String username){

        geminiPromptResponse = geminiPromptResponse.replace("'", "''");
        String uuid = UUID.randomUUID().toString();

        JSONObject profileJson = new JSONObject(geminiPromptResponse);
        JSONObject superheroJson = profileJson.getJSONObject("superhero");
        String superhero = superheroJson.getString("name");

        String sqlQuery = String.format("INSERT INTO user_profile"+
                "(profile_id, username, superhero_name, profile)"+
                "VALUES('%s', '%s', '%s', '%s')",
                uuid, username, superhero, geminiPromptResponse
        );

        Connection connection = connectingDB();//established connection with db and java
        if(connection == null){
            System.out.println("[StoreProfileInDatabase] Could not send data to database");
            return;
        }
        try{
            Statement statement = connection.createStatement();// queries will be inserted to db using this. acts as a pipe
            statement.execute(sqlQuery);
            System.out.println("data successfully inserted into database");
        }
        catch (Exception e){
            System.out.println("[StoreProfileInDatabase] error while sending data to database");
            e.printStackTrace();
        }
    }
}
