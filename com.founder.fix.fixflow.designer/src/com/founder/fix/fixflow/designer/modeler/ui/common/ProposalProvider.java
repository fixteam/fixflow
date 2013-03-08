/*package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.eclipse.codeassist.processors.IProposalProvider;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;

public class ProposalProvider implements IProposalProvider {
	@SuppressWarnings("unused")
	private ContentAssistContext context;
	@SuppressWarnings("unused")
	private int offset ;
	private ASTNode completionNode;

	public ProposalProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IGroovyProposal> getStatementAndExpressionProposals(
			ContentAssistContext context, ClassNode compleType,
			boolean isStatic, Set<ClassNode> categories) {
		// TODO Auto-generated method stub
		this.context = context ;
		offset = context.completionLocation ;
		completionNode = context.completionNode ;
		
		List<IGroovyProposal> groovyProposals = new LinkedList<IGroovyProposal>();
		if(completionNode != null){
			if(completionNode instanceof Expression){

				ClassNode completionType =((Expression) completionNode).getType() ;
				List<FieldNode> fieldlist = new ArrayList<FieldNode>();
				
				String type = completionType.getName();
				if(type.equals(Object.class.getName())){
					for(FieldNode node : fieldlist){
						if(node.getName().equals(completionNode.getText())){
							completionType = node.getType() ;
							break;
						}
					}
				}
			}
		}
		return groovyProposals;
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

}
*/