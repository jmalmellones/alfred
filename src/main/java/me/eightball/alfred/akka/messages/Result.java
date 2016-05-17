package me.eightball.alfred.akka.messages;

/**
 * Created by jmalmellones on 11/5/16.
 */
public class Result {

    private final double value;

    public Result(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
