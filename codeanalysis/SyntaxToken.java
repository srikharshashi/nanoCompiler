package codeanalysis;

// represents a word in a language

import java.util.ArrayList;
import java.util.List;


public class SyntaxToken extends SyntaxNode {

    private SyntaxKind _syntaxKind;
    private int _position;
    private String _text;
    private Object _value;

    SyntaxToken(SyntaxKind syntaxKind, int position, String text, Object value) {
        _text = text;
        _position = position;
        _syntaxKind = syntaxKind;
        _value = value;
    }

    SyntaxKind getSyntaxKind() {
        return this._syntaxKind;
    }

    int getPosition() {
        return this._position;
    }

    String getText() {
        return this._text;
    }

    Object getValue() {
        return this._value;
    }

    @Override
    public SyntaxKind getKind() {
        return _syntaxKind;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return new ArrayList<SyntaxNode>();
    }

}