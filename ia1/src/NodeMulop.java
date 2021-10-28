/**
 * Node for the mulop terminal in our grammar, can contain either * or /
 */
public class NodeMulop extends Node {

    private String mulop;

    public NodeMulop(int pos, String mulop) {
        this.pos = pos;
        this.mulop = mulop;
    }

    /**
     * Perform either multiplication or division
     *
     * @param o1 - Number one for operation contained within a fact
     * @param o2 - Number one for operation contained within a fact
     * @return - The product or quotient of the numbers passed
     * @throws EvalException
     */
    public double op(double o1, double o2) throws EvalException {
        if (mulop.equals("*"))
            return o1 * o2;
        if (mulop.equals("/"))
            return o1 / o2;
        throw new EvalException(pos, "bogus mulop: " + mulop);
    }

}
