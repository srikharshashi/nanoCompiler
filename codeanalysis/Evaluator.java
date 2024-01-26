package codeanalysis;

public class Evaluator {
    private final ExpressionSyntax _root;

    public Evaluator(ExpressionSyntax root) {
        _root = root;
    }

    public int evaluate() {
        int val = 0;
        try {
            val = evaluateExpression(_root);
        } catch (Exception e) {
            System.out.println("EVALUATOR : " + e);
        }
        return val;
    }

    private int evaluateExpression(ExpressionSyntax node) throws Exception {
        if (node instanceof LiteralExpressionSyntax _node) {
            // System.out.println("abc");
            return (int) _node.getLiteralToken().getValue();
        } else if (node instanceof UnaryExpressionSyntax _node) {
            var operand = evaluateExpression(_node.get_operand());
            if (_node.get_operator().getKind() == SyntaxKind.PlusToken) return operand;
            else if (_node.get_operator().getKind() == SyntaxKind.MinusToken) return -operand;
            else throw new Exception("EVALUATOR:  Unexpected operator : " + _node.get_operator());
        } else if (node instanceof BinaryExpressionSyntax _node) {
            int left = evaluateExpression(_node.get_left());
            int right = evaluateExpression(_node.get_right());
            SyntaxToken operatorToken = _node.get_operator();
            if (operatorToken.getKind() == SyntaxKind.BackSlashToken && right == 0)
                throw new Exception("EVALUATOR : Possible Division by Zero");
            return switch (operatorToken.getKind()) {
                case PlusToken -> left + right;
                case MinusToken -> left - right;
                case StarToken -> left * right;
                case BackSlashToken -> left / right;
                default -> throw new Exception("EVALUATOR : Unexpected Binary Operator :" + operatorToken.getKind());
            };
        } else if (node instanceof ParanthesizedExpressionSyntax p) {
            return evaluateExpression(p.get_expressionSyntax());
        } else throw new Exception("EVALUATOR:Unexpected Node : " + node.getKind());

    }

}