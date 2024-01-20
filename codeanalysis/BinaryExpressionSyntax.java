package codeanalysis;


// non -leaf node

import java.util.ArrayList;
import java.util.List;


public final class BinaryExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken _operator;
    private ExpressionSyntax _right;
    private ExpressionSyntax _left;

    public SyntaxToken get_operator() {
        return _operator;
    }

    public ExpressionSyntax get_right() {
        return _right;
    }

    public ExpressionSyntax get_left() {
        return _left;
    }

    BinaryExpressionSyntax(ExpressionSyntax left, ExpressionSyntax right, SyntaxToken operator) {
        _left = left;
        _right = right;
        _operator = operator;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.BinaryExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        var children = new ArrayList<SyntaxNode>();
        children.add(_left);
        children.add(_operator);
        children.add(_right);
        return children;

    }

}