// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

public class Scanner {

    private String program;    // source program being interpreted
    private int pos;        // index of next char in program
    private Token token;    // last/current scanned token

    // sets of various characters and lexemes
    private Set<String> whitespace = new HashSet<String>();
    private Set<String> digits = new HashSet<String>();
    private Set<String> letters = new HashSet<String>();
    private Set<String> legits = new HashSet<String>();
    private Set<String> keywords = new HashSet<String>();
    private Set<String> operators = new HashSet<String>();
    // set for comments
    private Set<String> comments = new HashSet<String>();

    // initializers for previous sets

    /**
     * Help function to fill in chars and digits within a range
     *
     * @param s  - String that might get filled
     * @param lo - Low end of the range
     * @param hi - Upper end of the range
     */
    private void fill(Set<String> s, char lo, char hi) {
        for (char c = lo; c <= hi; c++)
            s.add(c + "");
    }

    /**
     * Fills a set that is being used by the scanner to define valid whitespace characters
     *
     * @param s - The set
     */
    private void initWhitespace(Set<String> s) {
        s.add(" ");
        s.add("\n");
        s.add("\t");
    }

    /**
     * Fills the set that is used by the scanner to determine valid digits
     *
     * @param s - The set
     */
    private void initDigits(Set<String> s) {
        fill(s, '0', '9');
    }

    /**
     * Fills the set that used by the scanner to determine valid letters
     *
     * @param s - The set
     */
    private void initLetters(Set<String> s) {
        fill(s, 'A', 'Z');
        fill(s, 'a', 'z');
    }

    /**
     * Fills the set used by the scanner to validate operands
     *
     * @param s - The set
     */
    private void initLegits(Set<String> s) {
        s.addAll(letters);
        s.addAll(digits);
    }

    /**
     * Fills the set that is used by the scanner to determine operators
     *
     * @param s - The set
     */
    private void initOperators(Set<String> s) {
        s.add("=");
        s.add("+");
        s.add("-");
        s.add("*");
        s.add("/");
        s.add("(");
        s.add(")");
        s.add(";");
    }

    /**
     * Fills the set that is used by the scanner to determine keywords
     *
     * @param s - The set
     */
    private void initKeywords(Set<String> s) {
    }

    /**
     * Fills the set that used by the scanner to determine comments
     *
     * @param s - The set
     */
    private void initComments(Set<String> s) {
        s.add("#");
    }

    // constructor:
    //   - squirrel-away source program
    //   - initialize sets

    /**
     * Takes input program, scans through program looking for tokens to generate.
     * Uses a position counter to keep track of its location while it is scanning
     *
     * @param program - Input program from command line input
     */
    public Scanner(String program) {
        this.program = program;
        pos = 0;
        token = null;
        initWhitespace(whitespace);
        initDigits(digits);
        initLetters(letters);
        initLegits(legits);
        initKeywords(keywords);
        initOperators(operators);
        initComments(comments);
    }

    // handy string-processing methods

    /**
     * Function that notifies the scanner when it is done scanning input program
     *
     * @return - The position
     */
    public boolean done() {
        return pos >= program.length();
    }

    /**
     * Tries to make the largest, meaningful, and valid token by incrementing through position
     * Does this until a new unknown character is discovered
     *
     * @param s - The set being checked
     */
    private void many(Set<String> s) {
        while (!done() && s.contains(program.charAt(pos) + ""))
            pos++;
    }

    /**
     * Checks to see if we have gone past a char
     *
     * @param c - The char being checked to see if it has been passed
     */
    private void past(char c) {
        while (!done() && c != program.charAt(pos))
            pos++;
        if (!done() && c == program.charAt(pos))
            pos++;
    }

    // scan various kinds of lexeme

    /**
     * Scans the next number and creates a token out of it
     */
    private void nextNumber() {
        int old = pos;
        many(digits);
        token = new Token("num", program.substring(old, pos));
    }

    /**
     * Tries to make largest, valid, meaningful id out of letters and digits to try and make a token
     */
    private void nextKwId() {
        int old = pos;
        many(letters);
        many(legits);
        String lexeme = program.substring(old, pos);
        token = new Token((keywords.contains(lexeme) ? lexeme : "id"), lexeme);
    }

    /**
     * Creates a token out of the next op
     */
    private void nextOp() {
        int old = pos;
        pos = old + 2;
        if (!done()) {
            String lexeme = program.substring(old, pos);
            if (operators.contains(lexeme)) {
                token = new Token(lexeme); // two-char operator
                return;
            }
        }
        pos = old + 1;
        String lexeme = program.substring(old, pos);
        token = new Token(lexeme); // one-char operator
    }

    // This method determines the kind of the next token (e.g., "id"),
    // and calls a method to scan that token's lexeme (e.g., "foo").
    public boolean next() {
        many(whitespace);
        if (done()) {
            token = new Token("EOF");
            return false;
        }
        String c = program.charAt(pos) + "";
        if (digits.contains(c))
            nextNumber();
        else if (letters.contains(c))
            nextKwId();
        else if (operators.contains(c))
            nextOp();
        else if (comments.contains(c)) { //Adding comment functionality
            pos++;
            past(c.charAt(0));
            next();
        } else {
            System.err.println("illegal character at position " + pos);
            pos++;
            return next();
        }
        return true;
    }

    // This method scans the next lexeme,
    // if the current token is the expected token.
    public void match(Token t) throws SyntaxException {
        if (!t.equals(curr()))
            throw new SyntaxException(pos, t, curr());
        next();
    }

    public Token curr() throws SyntaxException {
        if (token == null)
            throw new SyntaxException(pos, new Token("ANY"), new Token("EMPTY"));
        return token;
    }

    public int pos() {
        return pos;
    }

    // for unit testing
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(args[0]);
            while (scanner.next())
                System.out.println(scanner.curr());
        } catch (SyntaxException e) {
            System.err.println(e);
        }
    }

}
