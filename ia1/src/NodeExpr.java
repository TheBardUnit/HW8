/**
 * "expr terminal node. This node is a higher level datatype according to our grammar.
 * Contains the addops and exprs
 */
public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
        this.term = term;
        this.addop = addop;
        this.expr = expr;
    }

    /**
     * A Function that allows an expression to contain another expression
     *
     * @param expr - the expression that is going to be combined
     */
    public void append(NodeExpr expr) {
        if (this.expr == null) {
            this.addop = expr.addop;
            this.expr = expr;
            expr.addop = null;
        } else
            this.expr.append(expr);
    }

    /**
     * Evaluates the given expression
     *
     * @param env - Environment that contains the variables being operated on
     * @return - The value of the term, or the value of whatever is being contained
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return expr == null
                ? term.eval(env)
                : addop.op(expr.eval(env), term.eval(env));
    }

}
