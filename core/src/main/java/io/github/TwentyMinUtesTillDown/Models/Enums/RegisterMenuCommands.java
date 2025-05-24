package io.github.TwentyMinUtesTillDown.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {

    PASSWORD_PATTERN("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,12}$"),
    USERNAME_PATTERN("^[a-zA-Z][a-zA-Z0-9_-]{2,19}$"),
    EMAIL_PATTERN("^[A-Za-z\\d\\.](?:[A-Za-z\\d]*\\.)?[A-Za-z\\d]*@[a-z]+\\.com$");

    private String pattern;
    RegisterMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public String extractGroup(Matcher matcher, String groupName) {
        return (matcher != null && matcher.group(groupName) != null) ? matcher.group(groupName) : null;
    }
}
