/**
 * A node for the "fact" terminal contained within our grammar
 */
public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    public NodeFactExpr(NodeExpr expr) {

        this.expr = expr;
    }

    /**
     * @param env - Environment containing variables being operated on
     * @return - Value of the term, or whatever was contained in it
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return expr.eval(env);
    }

}
