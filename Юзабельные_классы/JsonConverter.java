package Юзабельные_классы;

import com.google.gson.Gson;

public class JsonConverter <T>{
    Gson gson = new Gson();


    public String converterToJson(T animal){
        String json = gson.toJson(animal);
        return json;
    }

    public T convertFromJson (String json, Class <T> type_class){
        return gson.fromJson(json, type_class);
    }
}

