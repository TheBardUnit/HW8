/**
 * A unary minus node, contains singular "-"
 */
public class NodeUnaryMin extends NodeFact {
    private NodeFact testFact = null;

    public NodeUnaryMin(NodeFact unaryMin) {
        this.testFact = unaryMin;
    }

    /**
     *
     * @param environment - Environment that contains variable
     * @return - evaluated environment based on unary minus
     * @throws EvalException
     */
    public double eval(Environment environment) throws EvalException {
        return -testFact.eval(environment);
    }

}
