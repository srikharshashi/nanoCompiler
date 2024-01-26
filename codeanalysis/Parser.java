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



 class Parser {

    private List<SyntaxToken> _tokens;
    private int _position;
    private List<String> _diagnostics = new ArrayList<>();

    Parser(String text) {
        Lexer lexer = new Lexer(text);
        List<SyntaxToken> tokens = new ArrayList<>();

        // Remember that EOF Tokens are enabled
        SyntaxToken token;
        do {
            token = lexer.Lex();
            if (token.getSyntaxKind() != SyntaxKind.WhiteSpaceToken && token.getSyntaxKind() != SyntaxKind.BadToken) {
                tokens.add(token);
            }
        } while (token.getSyntaxKind() != SyntaxKind.EndOfFileToken);
        // for(var tiken:tokens) System.out.println(tiken.getKind());

        _diagnostics.addAll(lexer.get_diagnostics());
        _tokens = tokens;
    }



    public List<String> get_diagnostics() {
        return _diagnostics;
    }

    private SyntaxToken peek(int offset) {
        int index = _position + offset;
        if (index >= _tokens.size()) return _tokens.get(_tokens.size() - 1);
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

    private SyntaxToken matchToken(SyntaxKind sKind) {
        if (current().getKind() == sKind) return nextToken();
        _diagnostics.add("Parser : ERROR : Unexpected Token : " + current().getKind() + " ,Expected : " + sKind);
        return new SyntaxToken(sKind, current().getPosition(), null, null);
    }

    private ExpressionSyntax parseExpression(int parentPrecedence) {
        var left = ParsePrimaryExpression();
        while (true) {
            int precedence = SyntaxFacts.getBinaryOperatorPrecedence(current().getKind());
            if (precedence == 0 || precedence <= parentPrecedence) break;
            var operatorToken = nextToken();
            var right = parseExpression(precedence);
            left = new BinaryExpressionSyntax(left, right, operatorToken);
        }
        return left;
    }

    public SyntaxTree parse() {

        var expression = parseExpression(0);
        var endofFileToken = matchToken(SyntaxKind.EndOfFileToken);
        return new SyntaxTree(_diagnostics, expression, endofFileToken);
    }

    private ExpressionSyntax ParsePrimaryExpression() {

        if (current().getKind() == SyntaxKind.BracketOpenToken) {
            var left = nextToken();
            var expression = parseExpression(0);
            var right = matchToken(SyntaxKind.BracketCloseToken);
            return new ParanthesizedExpressionSyntax(left, right, expression);
        }

        var numberToken = matchToken(SyntaxKind.NumberToken);
        return new LiteralExpressionSyntax(numberToken);
    }
}