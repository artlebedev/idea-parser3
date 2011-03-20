package ru.artlebedev.idea.plugins.parser.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserClass;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserDocTypeInfo;
import ru.artlebedev.idea.plugins.parser.lang.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.util.ParserChangeUtil;
import ru.artlebedev.idea.plugins.parser.util.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.util.lookup.ParserLookupUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 19.03.11
 * Time: 19:26
 */
public class ParserDocTypeInfoImpl extends ParserElementImpl implements ParserDocTypeInfo, PsiReference {
	public ParserDocTypeInfoImpl(ASTNode astNode) {
		super(astNode);
	}

	public PsiReference getReference() {
		return this;
	}

	public PsiElement getElement() {
		return this;
	}

	public String getName() {
		ASTNode nameNode = findNameNode();
		if (nameNode != null)
			return nameNode.getText();
		return null;
	}

	public int getTextOffset() {
		ASTNode name = findNameNode();
		return name != null ? name.getStartOffset() : super.getTextOffset();
	}


	public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
		ASTNode identifier = ParserChangeUtil.createNameIdentifier(getProject(), name);
		ASTNode nameNode = findNameNode();
		if (nameNode != null)
			getNode().replaceChild(nameNode, identifier);
		return this;
	}

	public ASTNode findNameNode() {
		return getNode().findChildByType(ParserTokenTypes.IDENTIFIER);
	}

	public TextRange getRangeInElement() {
		final ASTNode firstChild = findNameNode();
		return firstChild.getTextRange().shiftRight(-1 * getNode().getStartOffset());
	}

	@Nullable
	public PsiElement resolve() {
		Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();

		for (ParserFile parserFile : parserFiles) {
			ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFile, ParserClass.class);
			if (parserClass != null && parserClass.getName().equals(getName())) {
				return parserClass;
			}
		}

		List<PsiElement> psiElements = ParserResolveUtil.collectClassIncludes(getContainingFile());
		for (PsiElement element : psiElements) {
			ParserClass parserClass = (ParserClass) element;
			getProject().getComponent(ParserFileIndex.class).contributeClass(parserClass);
			if (parserClass.getName().equals(getName())) {
				return parserClass;
			}
		}

		return null;
	}

	public String getCanonicalText() {
		return null;
	}

	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
		return setName(newElementName);
	}

	public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
		return null;
	}

	public boolean isReferenceTo(PsiElement element) {
		return element instanceof ParserClassImpl && element == resolve();
	}

	public Object[] getVariants() {
		List<PsiElement> result = new ArrayList<PsiElement>();
		Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();
		result.addAll(ParserResolveUtil.getClassesFromFiles(parserFiles));
		return ParserLookupUtil.createSmartLookupItems(result);
	}

	public boolean isSoft() {
		return false;
	}

	public String toString() {
		return "ParserDocTypeInfo";
	}
}
