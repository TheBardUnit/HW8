/**
 * Node for storing assignments that can be found in our grammars statements
 */
public class NodeAssn extends Node {

    private String id;
    private NodeExpr expr;

    public NodeAssn(String id, NodeExpr expr) {
        this.id = id;
        this.expr = expr;
    }

    /**
     * Evaluates a given assignment on a assignment node
     *
     * @param env - Environment that contains the variables being operated on
     * @return - id, and the evaluated expression in the environment
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return env.put(id, expr.eval(env));
    }

}
