/**
 * A "term" terminal node. Contains facts, mulops, and terms
 */
public class NodeTerm extends Node {

    private NodeFact fact;
    private NodeMulop mulop;
    private NodeTerm term;

    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
        this.fact = fact;
        this.mulop = mulop;
        this.term = term;
    }

    /**
     * Allows the one term to contain another term
     *
     * @param term - The term to be attached to another term
     */
    public void append(NodeTerm term) {
        if (this.term == null) {
            this.mulop = term.mulop;
            this.term = term;
            term.mulop = null;
        } else
            this.term.append(term);
    }

    /**
     * Performs the division or multiplication
     *
     * @param env - Environment contains the variables being operated on
     * @return - value of the operation performed
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return term == null
                ? fact.eval(env)
                : mulop.op(term.eval(env), fact.eval(env));
    }

}
