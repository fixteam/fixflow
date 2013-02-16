package com.founder.fix.fixflow.designer.groovyeditor;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.codeassist.creators.FieldProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.creators.IProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.processors.IProposalProvider;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistLocation;

public class FixProposalProvider implements IProposalProvider {
	
	private ContentAssistContext context;
	private int offset ;
	private ASTNode completionNode;

	public FixProposalProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IGroovyProposal> getStatementAndExpressionProposals(
			ContentAssistContext context, ClassNode completionType,
			boolean isStatic, Set<ClassNode> categories) {
		// TODO Auto-generated method stub
		
		this.context = context ;
		offset = context.completionLocation ;
		completionNode = context.completionNode ;
		
		List<IGroovyProposal> groovyProposals = new LinkedList<IGroovyProposal>();
		List<IGroovyProposal> proposals = new LinkedList<IGroovyProposal>();
		
		IProposalCreator[] creators = new IProposalCreator[] { new FieldProposalCreator() };
		for (IProposalCreator creator : creators) {
			if(!(creator instanceof FieldProposalCreator)){//NPE In FieldProposalCreator
				groovyProposals.addAll(creator.findAllProposals(completionType, new HashSet<ClassNode>(Collections.singleton(completionType)), 
						context.completionExpression, isStatic(), isStatic));
			}
			
		}
		
		//Add methods
		if(context.completionNode instanceof VariableExpression){
			if(((VariableExpression)context.completionNode).isDynamicTyped())
				proposals.addAll(groovyProposals);
		}
		return proposals;
	}

	@Override
	public List<MethodNode> getNewMethodProposals(ContentAssistContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNewFieldProposals(ContentAssistContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * When completing a expression, static context exists only if a ClassExpression
	 * When completing a statement, static context exists if in a static method or field
	 * @return true iff static
	 */
	private boolean isStatic() {
		if (context.location == ContentAssistLocation.EXPRESSION) { 
			return completionNode instanceof StaticMethodCallExpression ||
			completionNode instanceof ClassExpression;
		} else if (context.location == ContentAssistLocation.STATEMENT) {
			AnnotatedNode annotated = context.containingDeclaration;
			if (annotated instanceof FieldNode) {
				return ((FieldNode) annotated).isStatic();
			} else if (annotated instanceof MethodNode) {
				return ((MethodNode) annotated).isStatic();
			}
		}
		return false;
	}

}
