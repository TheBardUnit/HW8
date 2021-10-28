public class EvalException extends Exception {

    private int pos;
    private String msg;

    /**
     * @param postion for parser/scanner to locate error message
     * @param msg     - the error message itself
     */
    public EvalException(int pos, String msg) {
        this.pos = pos;
        this.msg = msg;
    }

    /**
     * toString method for error message printing
     *
     * @return - the error message
     */
    public String toString() {
        return "eval error"
                + ", pos=" + pos
                + ", " + msg;
    }

}
