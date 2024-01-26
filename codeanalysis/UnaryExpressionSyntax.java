package codeanalysis;

import java.util.ArrayList;
import java.util.List;

public final class UnaryExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken _operator;

    private ExpressionSyntax _operand;

    UnaryExpressionSyntax(ExpressionSyntax operand, SyntaxToken operator) {
        _operand = operand;

        _operator = operator;
    }

    public SyntaxToken get_operator() {
        return _operator;
    }

    public ExpressionSyntax get_operand() {
        return _operand;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.UnaryExpression;
    }
    //TODO:Format SyntaxKind Enum

    @Override
    public List<SyntaxNode> getChildren() {
        var children = new ArrayList<SyntaxNode>();
        children.add(_operand);
        children.add(_operator);
        return children;

    }

}