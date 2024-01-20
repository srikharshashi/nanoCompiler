package codeanalysis;

/**
 * PrettyPrint
 */
public class PrettyPrint {

    public static  void pprint(SyntaxNode node, String indent, boolean isLast) {
        String marker = isLast ? "└──" : "├──";
        System.out.print(indent + marker);
        System.out.print(node.getKind());
        if (node instanceof SyntaxToken) {
            SyntaxToken t = (SyntaxToken) node;
            if (t.getValue() != null) {
                System.out.print(" " + t.getValue());
            }
        }

        System.out.println();

        indent += isLast ? "   " : "|   ";
        SyntaxNode lastChild = node.getChildren().size() > 0 ? node.getChildren().get(node.getChildren().size() - 1)
                : null;
        for (var child : node.getChildren()) {
            pprint(child, indent, child == lastChild);
        }
    }
    
}