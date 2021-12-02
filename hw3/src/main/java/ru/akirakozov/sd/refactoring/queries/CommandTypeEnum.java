package ru.akirakozov.sd.refactoring.queries;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ilyakirpichev
 */
public enum CommandTypeEnum {
    SUM("sum"),
    MAX("max"),
    MIN("min"),
    COUNT("count");

    private static final Map<String, Command> BY_QUERY = new HashMap<>();

    static {
        BY_QUERY.put("sum", new SumCommand());
        BY_QUERY.put("max", new MaxCommand());
        BY_QUERY.put("min", new MinCommand());
        BY_QUERY.put("count", new CountCommand());
    }

    public final String queryType;

    CommandTypeEnum(String queryType) {
        this.queryType = queryType;
    }

    public static Command commandByTypeElseThrow(String label) throws RuntimeException {
        if (BY_QUERY.containsKey(label)) {
            return BY_QUERY.get(label);
        }
        throw new RuntimeException("no such type of element");
    }
}
