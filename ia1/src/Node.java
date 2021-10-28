// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.

/**
 * Base node class for all nodes parsed by parser
 */
public abstract class Node {

    protected int pos = 0;

    /**
     * @param env - the stored variables from Environment class
     * @return - value of eval, in this case a double with change implemented currently
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        throw new EvalException(pos, "cannot eval() node!");
    }
}
