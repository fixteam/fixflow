package com.founder.fix.fixflow.designer.groovyeditor;

import java.util.List;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.ImportNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;

import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;

public class FixGroovyUtil {
	public static VariableScope getVariableScope(GroovyCompilationUnit unit) {
		if(unit != null){
			CompletionNodeFinder finder = new CompletionNodeFinder(0,0,0, "",""); //$NON-NLS-1$ //$NON-NLS-2$
			ContentAssistContext assistContext = finder.findContentAssistContext(unit);  

			org.codehaus.groovy.ast.ASTNode astNode = null ;
			if(assistContext != null){
				astNode = assistContext.containingCodeBlock ;
			}


			if (astNode instanceof BlockStatement) {
				return ((BlockStatement) astNode).getVariableScope();
			} else if (astNode instanceof ClassNode && ((ClassNode) astNode).isScript()) {
				// use scope of the run method
				ClassNode clazz = (ClassNode) astNode;
				MethodNode method = clazz.getMethod("run", new Parameter[0]); //$NON-NLS-1$
				if (method != null && method.getCode() instanceof BlockStatement) {
					return ((BlockStatement) method.getCode()).getVariableScope();
				}
			} else if( astNode instanceof ImportNode){
				int i = 0 ;
				while (astNode instanceof ImportNode){
					finder = new CompletionNodeFinder(i,0,0, "",""); //$NON-NLS-1$ //$NON-NLS-2$
					assistContext = finder.findContentAssistContext(unit);  
					astNode = assistContext.containingCodeBlock ;
					i++ ;
				}
				if (astNode instanceof ClassNode && ((ClassNode) astNode).isScript()) {
					// use scope of the run method
					ClassNode clazz = (ClassNode) astNode;
					MethodNode method = clazz.getMethod("run", new Parameter[0]); //$NON-NLS-1$
					if (method != null && method.getCode() instanceof BlockStatement) {
						return ((BlockStatement) method.getCode()).getVariableScope();
					}
				}else if(astNode instanceof BlockStatement){
					return ((BlockStatement) astNode).getVariableScope();
				}
			}
		}
		return null;

	}

	public static void addToVariableScope(GroovyCompilationUnit unit, List<FieldNode> nodes) {
		VariableScope scope = getVariableScope(unit);

		if(nodes!=null) {
			while (scope != null){
				for(FieldNode f : nodes){
					scope.putDeclaredVariable(f);
				}
				scope = scope.getParent() ;
			}
		}
		
	}
}
