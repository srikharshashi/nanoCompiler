package codeanalysis;

import java.util.ArrayList;
import java.util.List;


/**
 * SyntaxNode --> A base class for all nodes in the syntax tree
 */

 /*
 * 1 + 2 * 3 --> Parse Tree
 * +
 * / \
 * 1 *
 * / \
 * 2 3
 */

public class Parser {

    private List<SyntaxToken> _tokens;
    private int _position;
    private List<String> _diagnostics = new ArrayList<>();

    public List<String> get_diagnostics() {
        return _diagnostics;
    }

    Parser(String text) {
        Lexer lexer = new Lexer(text);
        List<SyntaxToken> tokens = new ArrayList<>();

        // Remember that EOF Tokens are enabled
        SyntaxToken token;
        do {
            token = lexer.nextToken();
            if (token.getSyntaxKind() != SyntaxKind.WhiteSpaceToken
                    && token.getSyntaxKind() != SyntaxKind.BadToken) {
                tokens.add(token);
            }
        } while (token.getSyntaxKind() != SyntaxKind.EndOfFileToken);
        // for(var tiken:tokens) System.out.println(tiken.getKind());

        _diagnostics.addAll(lexer.get_diagnostics());
        _tokens = tokens;
    }

    private SyntaxToken peek(int offset) {
        int index = _position + offset;
        if (index >= _tokens.size())
            return _tokens.get(_tokens.size() - 1);
        return _tokens.get(index);
    }

    private SyntaxToken current() {
        return peek(0);
    }

    private SyntaxToken nextToken() {
        SyntaxToken _current = current();
        _position++;
        return _current;
    }

    private SyntaxToken match(SyntaxKind sKind) {
        if (current().getKind() == sKind)
            return nextToken();
        _diagnostics.add("Parser : ERROR : Unexpected Token : " + current().getKind() + " ,Expected : " + sKind);
        return new SyntaxToken(sKind, current().getPosition(), null, null);
    }

    public SyntaxTree parse() {

        var expression = parseTerm();
        var endofFileToken = match(SyntaxKind.EndOfFileToken);
        return new SyntaxTree(_diagnostics, expression, endofFileToken);

    }

    public ExpressionSyntax parseTerm() {
        ExpressionSyntax left = parseFactor();
        while (current().getSyntaxKind() == SyntaxKind.PlusToken ||
                current().getSyntaxKind() == SyntaxKind.MinusToken) {
            SyntaxToken opeartorToken = nextToken();
            ExpressionSyntax right = parseFactor();
            left = new BinaryExpressionSyntax(left, right, opeartorToken);
        }

        return left;
    }

    public ExpressionSyntax parseFactor() {
        ExpressionSyntax left = ParsePrimaryExpression();
        while (current().getSyntaxKind() == SyntaxKind.StarToken ||
                current().getSyntaxKind() == SyntaxKind.BackSlashToken) {
            SyntaxToken opeartorToken = nextToken();
            ExpressionSyntax right = ParsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, right, opeartorToken);
        }
        return left;
    }

    private ExpressionSyntax parseExpression() {
        return parseTerm();
    }

    private ExpressionSyntax ParsePrimaryExpression() {

        if (current().getKind() == SyntaxKind.BracketOpenToken) {
            var left = nextToken();
            var expression = parseExpression();
            var right = match(SyntaxKind.BracketCloseToken);
            return new ParanthesizedExpressionSyntax(left, right, expression);
        }

        var numberToken = match(SyntaxKind.NumberToken);
        return new NumberExpressionSyntax(numberToken);
    }
}