package sperias.essential.entity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class PlayerApi {

    String name;
    String id;

    public PlayerApi(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public UUID getUUID()
    {
        return UUID.fromString(new StringBuilder(this.id)
                .insert(20, '-')
                .insert(16, '-')
                .insert(12, '-')
                .insert(8, '-')
                .toString());
    }

    public String getName()
    {
        return this.name;
    }

    public static PlayerApi fetchPlayerAPI(String playerName) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Gson gson = new Gson();
            return gson.fromJson(reader.readLine(), PlayerApi.class);
        }
        return null;
    }
}
