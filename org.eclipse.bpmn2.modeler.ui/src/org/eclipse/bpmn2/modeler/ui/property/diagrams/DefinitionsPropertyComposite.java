package org.eclipse.bpmn2.modeler.ui.property.diagrams;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.impl.DefinitionsImpl;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2TableComposite;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.dialogs.SchemaImportDialog;
import org.eclipse.bpmn2.modeler.ui.property.editors.TextAndButtonObjectEditor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DefinitionsPropertyComposite extends DefaultPropertiesComposite  {

	public DefinitionsPropertyComposite(Composite parent, int style) {
		super(parent, style);
	}

	private AbstractPropertiesProvider propertiesProvider;

	/**
	 * @param section
	 */
	public DefinitionsPropertyComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"name",
						"targetNamespace",
						"typeLanguage",
						"expressionLanguage",
						"exporter",
						"exporterVersion",
						"imports",
						"relationships"
				};
				
				@Override
				public String[] getProperties() {
					return properties; 
				}
			};
		}
		return propertiesProvider;
	}

	@Override
	protected void bindList(EObject object, EStructuralFeature feature) {
		if (modelEnablement.isEnabled(object.eClass(), feature)) {
			if ("imports".equals(feature.getName())) {
				ImportsTable importsTable = new ImportsTable(propertySection);
				importsTable.bind();
			}
			else if ("relationships".equals(feature.getName())) {
				AbstractBpmn2TableComposite table = new AbstractBpmn2TableComposite(propertySection, AbstractBpmn2TableComposite.DEFAULT_STYLE);
				table.bindList(getEObject(), feature);
			}
			else {
				super.bindList(object, feature);
			}
		}
	}

	public class ImportsTable extends AbstractBpmn2TableComposite {

		/**
		 * @param parent
		 * @param style
		 */
		public ImportsTable(AbstractBpmn2PropertySection section) {
			super(section, DEFAULT_STYLE);
		}


		public void bind() {
			DefinitionsImpl definitions = (DefinitionsImpl)getEObject();
			EStructuralFeature imports = definitions.eClass().getEStructuralFeature("imports");
			
			super.bindList(definitions, imports);
		}

		
		@Override
		public AbstractTableColumnProvider getColumnProvider(EObject object, EStructuralFeature feature) {
			if (columnProvider==null) {
				columnProvider = new AbstractTableColumnProvider() {
					@Override
					public boolean canModify(EObject object, EStructuralFeature feature, EObject item) {
						return false;
					}
				};
				
				// add a namespace prefix column that does NOT come from the Import object
				TableColumn tableColumn = new TableColumn(object,null) {
					@Override
					public String getHeaderText() {
						return "Namespace Prefix";
					}
	
					@Override
					public String getText(Object element) {
						Import imp = (Import)element;
						String prefix = NamespaceUtil.getPrefixForNamespace(imp.eResource(), imp.getNamespace());
						if (prefix!=null)
							return prefix;
						return "";
					}
				};
				columnProvider.add(tableColumn);
				// add remaining columns
				EClass eClass = PACKAGE.getImport();
				columnProvider.add(new TableColumn(object,
						(EAttribute)eClass.getEStructuralFeature("namespace")));
				columnProvider.add(new TableColumn(object,
						(EAttribute)eClass.getEStructuralFeature("location")));
				columnProvider.add(new TableColumn(object,
						(EAttribute)eClass.getEStructuralFeature("importType")));
			}
			return columnProvider;
		}


		@Override
		protected EObject editListItem(EObject object, EStructuralFeature feature) {
			return super.editListItem(object, feature);
		}


		@Override
		protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
			Definitions defs = (Definitions)object;
			EList<Import> list = (EList<Import>)object.eGet(feature);
			Import imp = list.get(index);
			boolean canRemoveNamespace = true;
			for (Import i : defs.getImports()) {
				if (i!=imp) {
					String loc1 = i.getLocation();
					String loc2 = imp.getLocation();
					String ns1 = i.getNamespace();
					String ns2 = imp.getNamespace();
					// different import locations, same namespace?
					if (loc1!=null && loc2!=null && !loc1.equals(loc2) &&
							ns1!=null && ns2!=null && ns1.equals(ns2)) {
						// this namespace is still in use by another import!
						canRemoveNamespace = false;
						break;
					}
				}
			}
			if (canRemoveNamespace)
				NamespaceUtil.removeNamespace(imp.eResource(), imp.getNamespace());
			return super.removeListItem(object, feature, index);
		}

		@Override
		protected EObject addListItem(EObject object, EStructuralFeature feature) {
			SchemaImportDialog dialog = new SchemaImportDialog(getShell(), object);
			if (dialog.open() == Window.OK) {
				Object result[] = dialog.getResult();
				if (result.length == 1) {
					return ModelHandler.addImport(object, result[0]);
				}
			}
			return null;
		}
	}
	
	public class ImportPropertiesComposite extends DefaultPropertiesComposite {

		private Text text;
		private Button button;
		
		public ImportPropertiesComposite(Composite parent, int style) {
			super(parent, style);
		}

		/**
		 * @param section
		 */
		public ImportPropertiesComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}
		
		@Override
		public void createBindings(EObject be) {
			final Import imp = (Import)be;
			
			Composite composite = getAttributesParent();
			TextAndButtonObjectEditor editor = new TextAndButtonObjectEditor(this,be,null) {

				@Override
				protected void buttonClicked() {
					IInputValidator validator = new IInputValidator() {

						@Override
						public String isValid(String newText) {
							String ns = NamespaceUtil.getNamespaceForPrefix(imp.eResource(), newText);
							if (ns==null)
								return null;
							return "Prefix "+newText+" is already used for namespace\n"+ns;
						}
						
					};
					String initialValue = getText();
					InputDialog dialog = new InputDialog(
							getShell(),
							"Namespace Prefix",
							"Enter a namespace prefix",
							initialValue,
							validator);
					if (dialog.open()==Window.OK){
						updateObject(dialog.getValue());
					}
				}
				
				protected boolean updateObject(final Object value) {
					// remove old prefix
					String prefix = text.getText();
					NamespaceUtil.removeNamespaceForPrefix(imp.eResource(), prefix);
					// and add new
					NamespaceUtil.addNamespace(imp.eResource(), (String)value, imp.getNamespace());
					setText((String) value);
					return true;
				}
				
				protected String getText() {
					return getNamespacePrefix();
				}
			};
			editor.createControl(composite,"Namespace Prefix",SWT.NONE);
			
			super.createBindings(be);
		}
		
		private String getNamespacePrefix() {
			Import imp = (Import)be;
			String prefix = NamespaceUtil.getPrefixForNamespace(imp.eResource(), imp.getNamespace());
			if (prefix==null)
				prefix = "";
			return prefix;
		}
	}
}
