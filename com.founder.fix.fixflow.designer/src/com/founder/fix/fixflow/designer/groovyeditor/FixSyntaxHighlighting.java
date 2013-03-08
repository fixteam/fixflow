package com.founder.fix.fixflow.designer.groovyeditor;

import java.util.List;

import org.codehaus.groovy.eclipse.editor.highlighting.IHighlightingExtender;
import org.eclipse.jface.text.rules.IRule;

public class FixSyntaxHighlighting implements IHighlightingExtender {

	public static final String[] KEYWORDS = new String[] { "class", "extends", "implements", "package", "return", "def", "try", "finally", "this", "new",
		"catch", "switch", "case", "default", "while", "if", "else", "elseif", "private", "protected", "final", "for", "in", "byte", "short", "break",
		"instanceof", "synchronized", "const", "float", "null", "throws", "do", "super", "with", "threadsafe", "transient", "native", "interface", "any",
		"double", "volatile", "as", "assert", "goto", "enum", "int", "boolean", "char", "false", "true", "static", "abstract", "continue", "import",
		"void", "long", "strictfp"};
	
	public FixSyntaxHighlighting() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getAdditionalGroovyKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAdditionalGJDKKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRule> getAdditionalRules() {
		// TODO Auto-generated method stub
		return null;
	}

}
