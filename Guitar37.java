public class Guitar37 implements Guitar {

    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int KEYBOARD_SIZE = KEYBOARD.length();

    private GuitarString[] guitarStrings; // Array of  the 37 guitar strings.
    private int timeSum; // Sum of the number of times the guitar was ticed.

    // Post: Constructs an array of the 37 guitar strings, which with their individual frequency.
    public Guitar37() {
        this.guitarStrings = new GuitarString[KEYBOARD_SIZE];
        for(int i=0; i< KEYBOARD_SIZE; i++){
            this.guitarStrings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));

        }
    }

    // Post: Converts the pitch to its corresponding note, and, if it is a valid value, plays it.
    public void playNote(int pitch) {
        int note = pitch + 24;
        if(note > 0 && note < KEYBOARD_SIZE) {
            this.guitarStrings[note].pluck();
        }
    }

    // Post: Returns whether the key is a valid key for this guitar (present in the keyboard).
    // Parameters: key is the char that is checked
    public boolean hasString(char key) {
        return KEYBOARD.indexOf(key) != -1;
    }

    // Pre: String must be key a valid key for this guitar (present in the keyboard),
    //      throws IllegalArgumentException if not.
    // Post: Plucks the string of the corresponding key.
    // Parameters: String determines which key should be played.
    public void pluck(char string){
        if (!hasString(string)) {
            throw new IllegalArgumentException();
        }
        this.guitarStrings[KEYBOARD.indexOf(string)].pluck();

    }

    // Post: Returns the sum of individual string samples.
    public double sample() {
        double sum = 0.0;

        for (GuitarString guitarstring : guitarStrings) {
            sum += guitarstring.sample();
        }

        return sum;
    }

    // Post: Tics each individual string, while also updating the timeSum counter.
    public void tic() {
        for (GuitarString guitarstring : guitarStrings) {
            guitarstring.tic();
        }
        timeSum++;
    }

    // Post: Returns the number of times all guitar strings were ticed.
    public int time() {
        return timeSum;
    }
}

