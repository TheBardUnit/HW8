// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

/**
 * The interpreters' parser that combines lexemes into our grammar
 */
public class Parser {

    private Scanner scanner;

    /**
     * Calls the scanners match function
     *
     * @param s - The token passed to be matched
     * @throws SyntaxException
     */
    private void match(String s) throws SyntaxException {
        scanner.match(new Token(s));
    }

    /**
     * Returns the current token being returned by the scanner
     *
     * @return - Current token being returned by the scanner
     * @throws SyntaxException
     */
    private Token curr() throws SyntaxException {
        return scanner.curr();
    }

    /**
     * Returns the position of scanner back to parser
     *
     * @return - Character position of the scanner
     */
    private int pos() {

        return scanner.pos();
    }

    /**
     * Parses an mulop
     *
     * @return - A mullop node
     * @throws SyntaxException
     */
    private NodeMulop parseMulop() throws SyntaxException {
        if (curr().equals(new Token("*"))) {
            match("*");
            return new NodeMulop(pos(), "*");
        }
        if (curr().equals(new Token("/"))) {
            match("/");
            return new NodeMulop(pos(), "/");
        }
        return null;
    }

    /**
     * Parses an addop
     *
     * @return - A addop node
     * @throws SyntaxException
     */
    private NodeAddop parseAddop() throws SyntaxException {
        if (curr().equals(new Token("+"))) {
            match("+");
            return new NodeAddop(pos(), "+");
        }
        if (curr().equals(new Token("-"))) {
            match("-");
            return new NodeAddop(pos(), "-");
        }
        return null;
    }

    /**
     * Parses a fact
     *
     * @return - A fact node
     * @throws SyntaxException
     */
    private NodeFact parseFact() throws SyntaxException {
        if (curr().equals(new Token("-"))) { // Parsing unary "-" symbol
            match("-");
            NodeFact fact = parseFact();
            return new NodeUnaryMin(fact);
        }
        if (curr().equals(new Token("("))) {
            match("(");
            NodeExpr expr = parseExpr();
            match(")");
            return new NodeFactExpr(expr);
        }
        if (curr().equals(new Token("id"))) {
            Token id = curr();
            match("id");
            return new NodeFactId(pos(), id.lex());
        }
        Token num = curr();
        match("num");
        return new NodeFactNum(num.lex());
    }

    /**
     * Parses a term
     *
     * @return - A term node
     * @throws SyntaxException
     */
    private NodeTerm parseTerm() throws SyntaxException {
        NodeFact fact = parseFact();
        NodeMulop mulop = parseMulop();
        if (mulop == null)
            return new NodeTerm(fact, null, null);
        NodeTerm term = parseTerm();
        term.append(new NodeTerm(fact, mulop, null));
        return term;
    }

    /**
     * Parses an expression
     *
     * @return - A expr node
     * @throws SyntaxException
     */
    private NodeExpr parseExpr() throws SyntaxException {
        NodeTerm term = parseTerm();
        NodeAddop addop = parseAddop();
        if (addop == null)
            return new NodeExpr(term, null, null);
        NodeExpr expr = parseExpr();
        expr.append(new NodeExpr(term, addop, null));
        return expr;
    }

    /**
     * Parses an assn
     *
     * @return - A assn node
     * @throws SyntaxException
     */
    private NodeAssn parseAssn() throws SyntaxException {
        Token id = curr();
        match("id");
        match("=");
        NodeExpr expr = parseExpr();
        NodeAssn assn = new NodeAssn(id.lex(), expr);
        return assn;
    }

    /**
     * Parses an stmt
     *
     * @return - A stmt node
     * @throws SyntaxException
     */
    private NodeStmt parseStmt() throws SyntaxException {
        NodeAssn assn = parseAssn();
        match(";");
        NodeStmt stmt = new NodeStmt(assn);
        return stmt;
    }

    /**
     * Parses the string input program
     *
     * @param program - The input program to parse
     * @return - Some form of node, depends on what gets past as a program
     * @throws SyntaxException
     */
    public Node parse(String program) throws SyntaxException {
        scanner = new Scanner(program);
        scanner.next();
        NodeStmt stmt = parseStmt();
        match("EOF");
        return stmt;
    }

}
