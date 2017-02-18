package jinsist.matchers;

import static java.util.Arrays.asList;

import java.util.List;

public class Arguments {
    private List<ArgumentMatcher> argumentMatchers;

    public Arguments(List<ArgumentMatcher> argumentMatchers) {
        this.argumentMatchers = argumentMatchers;
    }

    public boolean matches(Object... arguments) {
        int length = argumentMatchers.size();
        if (length != arguments.length)
            return false;
        else
            for (int i = 0; i < length; i++) {
                ArgumentMatcher matcher = argumentMatchers.get(i);
                boolean matches = matcher.matches(arguments[i]);
                if (!matches) return false;
            }
        return true;
    }

    public List<ArgumentMatcher> getArgumentMatchers() {
        return argumentMatchers;
    }

    @Override
    public boolean equals(Object other) {
        return ValueObjects.equal(this,other, o -> toList().equals(o.toList()));
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }
    
    private List<?> toList() {
        return asList(argumentMatchers);
    }
}
