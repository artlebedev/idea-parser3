package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import ru.artlebedev.idea.plugins.parser.lang.psi.api.ParserString;

public class ParserStringImpl extends ParserElementImpl implements ParserString {
    public ParserStringImpl(ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "ParserString";
    }
}