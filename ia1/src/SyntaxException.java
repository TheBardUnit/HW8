public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    /**
     *
     * @param pos - Position of error for parser/scanner
     * @param expected - Expected token
     * @param found - What the token is currently
     */
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    /**
     * toString method for custom error reporting format
     * @return
     */
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
