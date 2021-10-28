/**
 * Node for the "fact" terminal of our grammar that contains an identifier
 */
public class NodeFactId extends NodeFact {

    private String id;

    public NodeFactId(int pos, String id) {
        this.pos = pos;
        this.id = id;
    }

    /**
     * Evaluates the fact by its id by getting its position from the "env"
     *
     * @param env - Environment that contains the id
     * @return - The id from the environment and the pos
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return env.get(pos, id);
    }

}
