package com.founder.fix.fixflow.designer.groovyeditor;

import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;

public class FixGroovyEditor extends GroovyEditor {
	public FixGroovyEditor(IPreferenceStore store){
		setPreferenceStore(store);
	}

	public void editorContextMenuAboutToShow(IMenuManager menu) {
		menu.dispose() ;
	}

	@Override
	protected void createUndoRedoActions() {

	}
}
