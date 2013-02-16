package org.eclipse.bpmn2.modeler.ui.preferences;

import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class Bpmn2HomePreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {
	
	public Bpmn2HomePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Messages.Bpmn2PreferencePage_HomePage_Description);
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {
		String[][] entries = new String[TargetRuntime.getAllRuntimes().length][2];
		int i = 0;
		for (TargetRuntime r : TargetRuntime.getAllRuntimes()) {
			entries[i][0] = r.getName();
			entries[i][1] = r.getId();
			++i;
		}

		SelectableComboFieldEditor targetRuntimes = new SelectableComboFieldEditor(
				Bpmn2Preferences.PREF_TARGET_RUNTIME,
				Bpmn2Preferences.PREF_TARGET_RUNTIME_LABEL,
				entries,
				getFieldEditorParent());

		targetRuntimes.setPreferenceStore(getPreferenceStore());
//		targetRuntimes.setSelectedValue(getPreferenceStore().getString(Bpmn2Preferences.PREF_TARGET_RUNTIME));
		
		addField(targetRuntimes);

		BooleanFieldEditor showAdvancedPropsTab = new BooleanFieldEditor(
				Bpmn2Preferences.PREF_SHOW_ADVANCED_PROPERTIES,
				Bpmn2Preferences.PREF_SHOW_ADVANCED_PROPERTIES_LABEL,
				getFieldEditorParent());
		addField(showAdvancedPropsTab);

		BooleanFieldEditor showDescriptions = new BooleanFieldEditor(
				Bpmn2Preferences.PREF_SHOW_DESCRIPTIONS,
				Bpmn2Preferences.PREF_SHOW_DESCRIPTIONS_LABEL,
				getFieldEditorParent());
		addField(showDescriptions);

		BooleanFieldEditor expandProperties = new BooleanFieldEditor(
				Bpmn2Preferences.PREF_EXPAND_PROPERTIES,
				Bpmn2Preferences.PREF_EXPAND_PROPERTIES_LABEL,
				getFieldEditorParent());
		addField(expandProperties);

		Group group = new Group(getFieldEditorParent(), SWT.BORDER);
		group.setLayout(new GridLayout(3,false));
		group.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
		group.setText("Default values for BPMN Diagram Interchange (DI) optional attributes");

		BPMNDIAttributeDefaultComboFieldEditor isHorizontal = new BPMNDIAttributeDefaultComboFieldEditor(
				Bpmn2Preferences.PREF_IS_HORIZONTAL,
				Bpmn2Preferences.PREF_IS_HORIZONTAL_LABEL,
				group);
		addField(isHorizontal);

		BPMNDIAttributeDefaultComboFieldEditor isExpanded = new BPMNDIAttributeDefaultComboFieldEditor(
				Bpmn2Preferences.PREF_IS_EXPANDED,
				Bpmn2Preferences.PREF_IS_EXPANDED_LABEL,
				group);
		addField(isExpanded);

		BPMNDIAttributeDefaultComboFieldEditor isMessageVisible = new BPMNDIAttributeDefaultComboFieldEditor(
				Bpmn2Preferences.PREF_IS_MESSAGE_VISIBLE,
				Bpmn2Preferences.PREF_IS_MESSAGE_VISIBLE_LABEL,
				group);
		addField(isMessageVisible);

		BPMNDIAttributeDefaultComboFieldEditor isMarkerVisible = new BPMNDIAttributeDefaultComboFieldEditor(
				Bpmn2Preferences.PREF_IS_MARKER_VISIBLE,
				Bpmn2Preferences.PREF_IS_MARKER_VISIBLE_LABEL,
				group);
		addField(isMarkerVisible);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void performDefaults() {
		Bpmn2Preferences.getInstance().restoreDefaults(false);
		super.performDefaults();
	}
	
}