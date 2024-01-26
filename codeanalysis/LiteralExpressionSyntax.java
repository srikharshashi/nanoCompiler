package codeanalysis;

// leaf node

import java.util.ArrayList;
import java.util.List;


public final class LiteralExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken _numberToken;

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.LiteralExpression;
    }

    LiteralExpressionSyntax(SyntaxToken literalToken) {
        this._numberToken = literalToken;
    }

    public SyntaxToken getLiteralToken() {
        return _numberToken;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        var children = new ArrayList<SyntaxNode>();
        children.add(_numberToken);
        return children;
    }
}