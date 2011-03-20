package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDocParameterInfo;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserMethod;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObject;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserObjectAndMethodReference;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserParameter;
import ru.artlebedev.idea.plugins.parser.psi.lookup.ParserLookupUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2011 Valeriy Yatsko
 * Copyright (c) 2011 ArtLebedev Studio
 * User: dwr
 * Date: 19.03.11
 * Time: 19:31
 */
public class ParserObjectAndMethodReferenceImpl extends ParserElementImpl implements ParserObjectAndMethodReference, PsiReference {
	public ParserObjectAndMethodReferenceImpl(ASTNode astNode) {
		super(astNode);
	}

	public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
		return null;
	}

	public PsiElement getElement() {
		return this;
	}

	public PsiReference getReference() {
		return this;
	}

	public TextRange getRangeInElement() {
		final PsiElement firstChild = getFirstChild();
		final int startOffsetInParent = firstChild.getStartOffsetInParent();
		return new TextRange(startOffsetInParent, startOffsetInParent + getNode().getTextLength());
	}

	@Nullable
	public PsiElement resolve() {
		return null;
	}

	public String getCanonicalText() {
		return null;
	}

	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
		return null;
	}

	public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
		return null;
	}

	public boolean isReferenceTo(PsiElement element) {
		return false;
	}

	public Object[] getVariants() {
		PsiElement prevSibling = getPrevSibling();

		while (!(prevSibling instanceof ParserObjectReferenceImpl)) {
			prevSibling = prevSibling.getPrevSibling();
		}

		ParserObjectReferenceImpl parserObjectReference = (ParserObjectReferenceImpl) prevSibling;
		PsiElement resolveResult = parserObjectReference.resolve();
		if (resolveResult instanceof ParserObject) {
			ParserObject parserObject = (ParserObject) resolveResult;
			if (parserObject == null)
				return new Object[0];

			ParserClass type = parserObject.getType();
			if (type != null) {
				ParserMethod[] methods = type.getMethods();
				List<PsiElement> list = new ArrayList<PsiElement>();
				for (ParserMethod method : methods) {
					list.add(method);
				}
				return ParserLookupUtil.createSmartLookupItems(list);
			}
		}
		if (resolveResult instanceof ParserParameter) {
			String paramName = resolveResult.getText();
//			ParserMethod parserMethod = PsiTreeUtil.getParentOfType(resolveResult, ParserMethod.class, true, true);
      ParserMethod parserMethod = PsiTreeUtil.getParentOfType(resolveResult, ParserMethod.class, true);
			if (parserMethod != null) {
				ParserDocParameterInfo[] info = parserMethod.getParameterInfo();
				for (ParserDocParameterInfo parameterInfo : info) {
					if (parameterInfo.getName().equals(paramName)) {
						ParserClass[] parserClasses = parameterInfo.getType();
						List<PsiElement> list = new ArrayList<PsiElement>();
						for (ParserClass parserClass : parserClasses) {
							list.addAll(Arrays.asList(parserClass.getMethods()));
						}
						return ParserLookupUtil.createSmartLookupItems(list);
					}
				}
			}
		}
		return new Object[0];
	}

	public boolean isSoft() {
		return false;
	}
}
