package learn.welcome.domain;

import java.util.ArrayList;
import java.util.List;

//<T> placeholder - takes any type of class inside
public class Response<T> {

    //fields
    private ArrayList<String> messages = new ArrayList<>();

    //methods
    public boolean isSuccess() {return messages.size() == 0;}

    public List<String> getErrorMessages() {return new ArrayList<>(messages);}

    public void addErrorMessages(String message) {messages.add(message);}

}

