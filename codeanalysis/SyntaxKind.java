package codeanalysis;

public enum SyntaxKind {

    //Tokens
    EndOfFileToken,
    BadToken,
    NumberToken,
    WhiteSpaceToken,

    //operators
    PlusToken,
    MinusToken,
    StarToken,
    BackSlashToken,
    BracketOpenToken,
    BracketCloseToken,

    //expressions
    BinaryExpression,
    LiteralExpression,
    UnaryExpression, ParenthesizedExpression
}