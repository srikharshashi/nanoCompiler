package codeanalysis;


import java.util.ArrayList;
import java.util.List;


public class Lexer {

    private String _text;
    private int _position;
    private List<String> _diagnostics = new ArrayList<>();

    public List<String> get_diagnostics() {
        return _diagnostics;
    }

    public void set_diagnostics(List<String> _diagnostics) {
        this._diagnostics = _diagnostics;
    }

    Lexer(String text) {
        this._text = text;
    }

    private char current() {
        if (_position >= _text.length())
            return '\0';
        return _text.charAt(_position);
    }

    private void Next() {
        _position++;
    }

    private int parseWithDefault(String number, int defaultVal, List<String> diagnostics) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            diagnostics.add(
                    "Lexer : ERROR : Cannot parse the value : " + number + " as int32 . Substituted default to be :");
            return defaultVal;
        }
    }

    public SyntaxToken nextToken() {
        if (_position >= _text.length())
            return new SyntaxToken(SyntaxKind.EndOfFileToken, _position, "\0", null);

        // + ,-, *, /, (, ),number,<whitespace>
        if (Character.isDigit(current())) {
            int start = _position;
            while (Character.isDigit(current()))
                Next();
            int end = _position;
            // System.out.println("start: "+start+" end:"+_position);
            String text = _text.substring(start, end);
            return new SyntaxToken(SyntaxKind.NumberToken, start, text, parseWithDefault(text, 0, _diagnostics));
        } else if (current() == ' ') {
            int start = _position;
            while (current() == ' ')
                Next();
            int end = _position; // end is one pointer ahead
            String text = _text.substring(start, end);
            return new SyntaxToken(SyntaxKind.WhiteSpaceToken, start, text, text);
        } else if (current() == '+')
            return new SyntaxToken(SyntaxKind.PlusToken, _position++, "+", null);
        else if (current() == '-')
            return new SyntaxToken(SyntaxKind.MinusToken, _position++, "-", null);
        else if (current() == '*')
            return new SyntaxToken(SyntaxKind.StarToken, _position++, "*", null);
        else if (current() == '/')
            return new SyntaxToken(SyntaxKind.BackSlashToken, _position++, "/", null);
        else if (current() == '(')
            return new SyntaxToken(SyntaxKind.BracketOpenToken, _position++, "(", null);
        else if (current() == ')')
            return new SyntaxToken(SyntaxKind.BracketCloseToken, _position++, ")", null);
        _diagnostics.add("Lexer : ERROR : Bad Character Input : " + current());
        return new SyntaxToken(SyntaxKind.BadToken, _position++, _text.substring(_position - 1, _position), null);
    }
}