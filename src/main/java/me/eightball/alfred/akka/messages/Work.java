package me.eightball.alfred.akka.messages;

/**
 * Created by jmalmellones on 11/5/16.
 */
public class Work {

    private final int start;
    private final int nrOfElements;

    public Work(int start, int nrOfElements) {
        this.start = start;
        this.nrOfElements = nrOfElements;
    }

    public int getStart() {
        return start;
    }

    public int getNrOfElements() {
        return nrOfElements;
    }
}
