package codeanalysis;

import java.util.List;

public final class SyntaxTree {

    private SyntaxToken _endofFileToken;

    public SyntaxToken get_endofFileToken() {
        return _endofFileToken;
    }

    public static SyntaxTree parse(String text) {
        Parser parser = new Parser(text);
        return parser.parse();
    }

    private List<String> _diagnostics;

    public List<String> get_diagnostics() {
        return _diagnostics;
    }

    private ExpressionSyntax _root;

    public ExpressionSyntax get_root() {
        return _root;
    }

    public SyntaxTree(List<String> diagnostics, ExpressionSyntax root, SyntaxToken endofFileToken) {
        _diagnostics = diagnostics;
        _root = root;
        _endofFileToken = endofFileToken;
    }
    // public
}