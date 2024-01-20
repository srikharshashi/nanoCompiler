package codeanalysis;

// paranthesis node

import java.util.ArrayList;
import java.util.List;


public final class ParanthesizedExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken _openParanthesisToken;

    public SyntaxToken get_openParanthesisToken() {
        return _openParanthesisToken;
    }

    private SyntaxToken _closeParanthesisToken;

    public SyntaxToken get_closeParanthesisToken() {
        return _closeParanthesisToken;
    }

    private ExpressionSyntax _expressionSyntax;

    public ExpressionSyntax get_expressionSyntax() {
        return _expressionSyntax;
    }

    ParanthesizedExpressionSyntax(SyntaxToken openParanthesisToken, SyntaxToken closeParanthesisToken,
            ExpressionSyntax expressionSyntax) {
        _openParanthesisToken = openParanthesisToken;
        _closeParanthesisToken = closeParanthesisToken;
        _expressionSyntax = expressionSyntax;

    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.ParanthesizedExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        var children = new ArrayList<SyntaxNode>();
        children.add(_openParanthesisToken);
        children.add(_expressionSyntax);
        children.add(_closeParanthesisToken);
        return children;
    }

}