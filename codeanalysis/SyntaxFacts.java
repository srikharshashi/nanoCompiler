package codeanalysis;

/*

    Unary Operator Precedence
       ---------
       | -1 * 3 | :- Expression
       ---------
            -
            |
            *          Tree 1 :- Unary Minus Precedence is lower
           /  \
          1    3

            *
           / \         Tree 2 :- Unary Minus Precedence is higher
          -   3
          |
          1
 */
    final class SyntaxFacts{
        public static int getBinaryOperatorPrecedence(SyntaxKind syntaxKind) {
            return switch (syntaxKind) {
                case  StarToken, BackSlashToken -> 2;
                case PlusToken, MinusToken-> 1;
                default -> 0;
            };
        }

        public static int getUnaryOperatorPrecedence(SyntaxKind syntaxKind){
            return switch (syntaxKind) {
                case PlusToken, MinusToken-> 3;
                default -> 0;
            };
        }

    }

