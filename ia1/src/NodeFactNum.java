/**
 * A "fact" terminal that contains a number
 */
public class NodeFactNum extends NodeFact {

    private String num;

    public NodeFactNum(String num) {
        this.num = num;
    }

    /**
     * Evaluates the "fact" number by getting its int value
     *
     * @param env - Environment that contains variables being operated on
     * @return - The parsed int contained within the environment
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(num);
    }

}
