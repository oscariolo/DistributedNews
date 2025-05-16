package com.springbootserver.distributednewsserver.service;

import java.util.Map;

public class TextToNewsProccesors {

    static String processSportsData(Map<String, Object> data) {
        // Implement your sports data processing logic here
        
        String scores = (String) data.get("scores");
        String teams = (String) data.get("teams");
        String date = (String) data.get("date");
        String location = (String) data.get("location");
        String reds = (String) data.get("reds");
        String yellows = (String) data.get("yellows");
        String highlights = (String) data.get("highlights");
        
        String newsHeadline = "Los equipos: " + teams + " jugaron el " + date + " en el estadio principal de " + location;
        String newsBody = "El partido terminó con un marcador de " + scores + ". " +
                "Los equipos recibieron " + reds + " tarjetas rojas y " + yellows + " tarjetas amarillas. " +
                "Los mejores momentos del partido fueron: " + highlights;

        return newsHeadline + "\n" + newsBody;
    }
    static String processPoliticsData(Map<String, Object> data) {
        
        String event = (String) data.get("event");
        String location = (String) data.get("location");
        String date = (String) data.get("date");
        String participants = (String) data.get("participants");
        String decisions = (String) data.get("decisions");
        String newsHeadline = "El evento político: " + event + " tuvo lugar en " + location + " el " + date;
        String newsBody = "Los participantes fueron: " + participants + ". " +
                "Las decisiones tomadas fueron: " + decisions;

        return newsHeadline + "\n" + newsBody;
    }
    static String processTechnologyData(Map<String, Object> data) {
        // Implement your technology data processing logic here
        return "Processed technology data: " + data.toString();
    }
    static String UnimplementedTopicData(Map<String, Object> data) {
        // Implement your unimplemented topic data processing logic here
        return "Unimplemented topic data: " + data.toString();
    }
}
