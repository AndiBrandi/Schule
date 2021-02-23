import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Getraenkerequest {

    public String readfile(){
        String result = "";
        try {
            BufferedReader objReader = new BufferedReader(new FileReader("response.json"));

            String line = "";

            while((line = objReader.readLine()) != null) {
                result += line; // result = result  + line;
            }

            objReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
