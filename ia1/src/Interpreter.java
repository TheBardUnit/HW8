// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

/**
 * Driver class for running the interpreter on command line input program
 */
public class Interpreter {

    /**
     * Main driver function for Interpreter class
     *
     * @param args - the source program broken up into parts
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        Environment env = new Environment();
        for (String stmt : args)
            try {
                System.out.println(parser.parse(stmt).eval(env));
            } catch (SyntaxException e) {
                System.err.println(e);
            } catch (EvalException e) {
                System.err.println(e);
            }
    }

}
