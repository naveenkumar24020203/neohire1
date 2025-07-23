package utils;

@FunctionalInterface
public interface SelectDateFunction {
    void select(String year, String month, String day, int hour, int minute, String amPm) throws InterruptedException;
}
