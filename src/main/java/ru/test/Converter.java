package ru.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class Converter is responsible for full YAML->JSON convertion
 * @author  Mikhaleva Maria
 * @version dated 27 Dec 2018
 */

class Converter {

    /**
     * Convertion YAML->JSON->XML procedure
     *
     * @param yamlString - full path to YAML-file
     */

    static String returnResult(String yamlString) {
        String content;
        JSONObject jsonObject = null;
        try{
            content = new String(Files.readAllBytes(Paths.get(yamlString)));
            String YAMLtoJSON = convertYamlToJson(content);
            jsonObject = new JSONObject(YAMLtoJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return XML.toString(jsonObject);
    }

    /**
     * Convertion YAML->JSON procedure
     *
     * @param yaml - YAML-file content
     */

    private static String convertYamlToJson(String yaml) {
        String res = "";
        try{
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);

            ObjectMapper jsonWriter = new ObjectMapper();
            res = jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        }
        catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return res;
    }

}
