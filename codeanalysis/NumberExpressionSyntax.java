package codeanalysis;

// leaf node

import java.util.ArrayList;
import java.util.List;


public final class NumberExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken _numberToken;

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NumberExpression;
    }

    NumberExpressionSyntax(SyntaxToken numberToken) {
        this._numberToken = numberToken;
    }

    public SyntaxToken getNumberToken() {
        return _numberToken;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        var children = new ArrayList<SyntaxNode>();
        children.add(_numberToken);
        return children;
    }
}