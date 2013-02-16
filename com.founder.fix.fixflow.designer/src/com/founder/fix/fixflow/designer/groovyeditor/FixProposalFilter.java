package com.founder.fix.fixflow.designer.groovyeditor;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.codeassist.processors.IProposalFilter;
import org.codehaus.groovy.eclipse.codeassist.proposals.GroovyMethodProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;

public class FixProposalFilter implements IProposalFilter {

	public FixProposalFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IGroovyProposal> filterProposals(List<IGroovyProposal> proposals,
			ContentAssistContext assist, JavaContentAssistInvocationContext context) {
		List<IGroovyProposal> filteredProposals = new ArrayList<IGroovyProposal>();
		for(IGroovyProposal p : proposals){
			if(p instanceof GroovyMethodProposal){
				if(!(((GroovyMethodProposal)p).getMethod().getDeclaringClass().getName().equals("org.codehaus.groovy.runtime.DefaultGroovyMethods")
						|| ((GroovyMethodProposal)p).getMethod().getDeclaringClass().getName().equals("org.codehaus.groovy.runtime.DefaultGroovyStaticMethods"))){
					filteredProposals.add(p);
				}
			}else{
				filteredProposals.add(p);
			}
		
		}
	
		return filteredProposals;
	}

}
