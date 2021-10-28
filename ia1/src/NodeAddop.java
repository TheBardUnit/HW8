/**
 * Node class for an addop terminal,
 * contains either a plus or minus sign as determined by grammar
 */
public class NodeAddop extends Node {

    private String addop;

    /**
     * @param pos   - Position for scanner/parser to find plus or minus node
     * @param addop - Which operation to be contained within the node
     */
    public NodeAddop(int pos, String addop) {
        this.pos = pos;
        this.addop = addop;
    }

    /**
     * @param o1 - First number for operation
     * @param o2 - Second number for operation
     * @return - The result of addition or subtraction
     * @throws EvalException - Exception if operation is something other than +, or -
     */
    public double op(double o1, double o2) throws EvalException {
        if (addop.equals("+"))
            return o1 + o2;
        if (addop.equals("-"))
            return o1 - o2;
        throw new EvalException(pos, "bogus addop: " + addop);
    }

}
