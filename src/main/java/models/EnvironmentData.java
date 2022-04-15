package models;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentData {
    public HashMap<String, String> environmentUrls;
    public HashMap<String, HashMap<String, String>> existingUsers;

    public EnvironmentData(String environment) {
        Map env = null;
        File fileenv = new File("./src/test/resources/testdata/" + environment + "_environment.yml");
        Yaml yaml = new Yaml();
        try {
            env = yaml.load(new FileInputStream(fileenv));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        environmentUrls = (HashMap<String, String>) env.get("environment_urls");
        existingUsers = (HashMap<String, HashMap<String, String>>) env.get("existing_users");
    }
}
