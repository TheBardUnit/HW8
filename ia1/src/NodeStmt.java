/**
 * Node that contains a statement
 */
public class NodeStmt extends Node {

    private NodeAssn assn;

    public NodeStmt(NodeAssn assn) {

        this.assn = assn;
    }

    /**
     * Evaluates a statement in an environment
     *
     * @param env - Environment that contains the variables for operation
     * @return - statement contained within the environment
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return assn.eval(env);
    }

}
