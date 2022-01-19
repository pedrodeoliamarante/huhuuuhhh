import java.util.*;

public class GuitarString {


    final static double ENERGYDECAYFACTOR = 0.996;

    private Queue<Double> ringBuffer;

    // Pre: Frequency must be bigger than 0 and the resulting size of the ring buffer must be bigger than 2.
    //      (throws IllegalArgumentException if not)
    // Post: Constructs a guitar string based on the given frequency
    // Parameters: frequency is the frequency of the desired guitar string.
    public GuitarString(double frequency) {
        if (frequency <= 0 || Math.round(StdAudio.SAMPLE_RATE / frequency) < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<>();
        double samplingRate = StdAudio.SAMPLE_RATE;
        double capacityN = Math.round(samplingRate / frequency);

        for (int i = 0; i < capacityN; i++) {
            ringBuffer.add(0.0);
        }
    }

    // Pre: The array init must have at least two elements.
    //      (throws IllegalArgumentException if not)
    // Post: Constructs a guitar string, initializing the contents of the ring buffer to
    //       the values in init.
    //       This is only used for testing purposes.
    // Parameters: Init is the double array that determines the values of the ring buffer.
    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        ringBuffer = new LinkedList<>();
        for (double doubles : init) {
            ringBuffer.add(doubles);
        }
    }

    // Post: Replaces the values of the ring buffer if random valid string lengths.
    public void pluck(){
        for (int i = 0; i < this.ringBuffer.size(); i++){
            this.ringBuffer.remove();
            this.ringBuffer.add(Math.random() - 0.5);
        }
    }

    // Post: Applies the the Karplus-Strong update once to the ring buffer.
    public void tic(){
        double temp = this.ringBuffer.remove();
        double newValue = ENERGYDECAYFACTOR * 1/2 * (temp + this.ringBuffer.peek());
        this.ringBuffer.add(newValue);
    }

    // Post: Returns the current sample, which is the first element of the ring buffer.
    public double sample(){
        return this.ringBuffer.peek();
    }


}
