package Юзабельные_классы;

import util.Command;

import java.util.Arrays;

public class Validator {
    private static final String CYRILLIC_REGEX = "[а-яёА-ЯЁ]+";
    private static final String ENG_REGEX = "[a-zA-Z]+";
    private static final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[0-2])[.](19[0-9][0-9]|20[0-9][0-9])";

    public boolean commandValidate(String command) {
        return Arrays.stream(Command.class.getEnumConstants()).anyMatch(e -> e.name().equals(command));
    }

    public boolean nameValidate (String name){
        return name.matches(CYRILLIC_REGEX) | name.matches(ENG_REGEX);
    }

    public boolean dateValidate (String date){
        return date.matches(DATA_REGEX);
    }
}
