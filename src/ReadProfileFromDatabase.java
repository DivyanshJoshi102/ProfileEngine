import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class ReadProfileFromDatabase {
    HashMap<Integer, String> profileMap = new HashMap<>();
    private Connection connectionDB(){
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
        catch (Exception e){
            System.out.println("[ReadProfileFromDatabase]error while establishing connection with data base");
            e.printStackTrace();
        }

        return null;
    }

    void pastProfileList(){
        Connection connection = connectionDB();
        if(connection == null){
            System.out.println("[ReadProfileFromDatabase] connection is null/empty while trying to read data from database");
            return;
        }
        try{
            String sqlQuery = "SELECT  username, superhero_name, timestamp, profile FROM user_profile";
            Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlQuery);
                int serialNumber = 1;
                System.out.printf("%-10s %-20s %-25s  %-25s %-30s%n",
                    "SNO.",
                    "Username",
                    "Superhero name",
                    "Date",
                    "Time");

                while (resultSet.next()) {
                    System.out.printf("%-10d", serialNumber);
                    OffsetDateTime timestampOfProfile = resultSet.getObject("timestamp", OffsetDateTime.class);
                    ZonedDateTime localTime =
                            timestampOfProfile.atZoneSameInstant(ZoneId.systemDefault());

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy_H:m_");
                    String dateTime = localTime.format(dateTimeFormatter);
                    String date = dateTime.substring(0,dateTime.indexOf("_"));
                    String time = dateTime.substring(dateTime.indexOf('_') +1, dateTime.lastIndexOf('_'));

                    System.out.printf(" %-20s %-25s %-25s %-30s%n",
                            resultSet.getString("username"),
                            resultSet.getString("superhero_name"),
                            date,
                            time);
                    profileMap.put(serialNumber, resultSet.getString("profile"));
                    serialNumber++;
                }

            System.out.println("\n[ReadProfileFromDatabase] successfully reading data from database");
            selectSerialNumberOfProfileStoredInDb();
        }
        catch (Exception e){
            System.out.println("[ReadProfileFromDatabase] error while reading data from database");
            e.printStackTrace();
        }
    }

    void selectSerialNumberOfProfileStoredInDb(){
        int choice = inputSerialNumberOfProfile();

        if(profileMap.containsKey(choice)){
            Connection connection = connectionDB();
            if(connection == null){
                System.out.println("[ReadProfileFromDatabase] connection is null/empty while trying to read data from database");
                return;
            }
            try{
                System.out.println(profileMap.get(choice));
            }
            catch (Exception e){
                System.out.println("[ReadProfleFromDatabase] error while selecting serial number of profile");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("enter valid serial number");
        }
    }

    int inputSerialNumberOfProfile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter serial number of profile you want to view: ");
        return scanner.nextInt();
    }
}