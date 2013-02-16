/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.preferences;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.ui.FeatureMap;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.service.prefs.BackingStoreException;


@SuppressWarnings("nls")
public class Bpmn2EditorPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	Bpmn2Preferences preferences;
	ListViewer elementsListViewer;
	List<Class> allElements;
	Composite styleEditors;
	Composite container;
	LinkedHashMap<Class, ShapeStyle> shapeStyles;
	Class currentSelection;
	ColorControl shapeBackground;
	ColorControl shapePrimarySelectedColor;
	ColorControl shapeSecondarySelectedColor;
	ColorControl shapeForeground;
	FontControl textFont;
	ColorControl textColor;
	boolean debug;

	public Bpmn2EditorPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Messages.Bpmn2PreferencePage_EditorPage_Description);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {

		preferences = Bpmn2Preferences.getInstance();

		allElements = new ArrayList<Class>();
		allElements.addAll(FeatureMap.CONNECTORS);
		allElements.addAll(FeatureMap.EVENTS);
		allElements.addAll(FeatureMap.GATEWAYS);
		allElements.addAll(FeatureMap.TASKS);
		allElements.addAll(FeatureMap.DATA);
		allElements.addAll(FeatureMap.OTHER);
		Collections.sort(allElements, new Comparator<Class>() {

			@Override
			public int compare(Class arg0, Class arg1) {
				return arg0.getSimpleName().compareTo(arg1.getSimpleName());
			}
			
		});
	}

	@Override
	protected Control createContents(Composite parent) {
		container = new Composite(parent, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,1,1));
        container.setLayout(new GridLayout(2, false));
        container.setFont(parent.getFont());
        
        elementsListViewer = new ListViewer(container, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        elementsListViewer.getControl().setFont(parent.getFont());
        GridData data = new GridData(SWT.FILL,SWT.TOP,true,false,1,1);
		data.heightHint = 200;
        elementsListViewer.getControl().setLayoutData(data);
        
        elementsListViewer.setContentProvider(new BEListContentProvider());
        elementsListViewer.setLabelProvider(new BEListLabelProvider());
        elementsListViewer.addSelectionChangedListener(new BEListSelectionChangedListener());
        
        styleEditors = new Composite(container, SWT.NONE);
        styleEditors.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,1,1));
        GridLayout layout = new GridLayout(1,false);
        layout.verticalSpacing = 0;
        styleEditors.setLayout(layout);
        styleEditors.setFont(parent.getFont());
        styleEditors.setVisible(false);

		shapeBackground = new ColorControl("&Fill Color:",styleEditors);
		shapeBackground.addSelectionListener( new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ShapeStyle ss = shapeStyles.get(currentSelection);
				IColorConstant c = shapeBackground.getSelectedColor();
				if (!ShapeStyle.compare(ss.getShapeBackground(),c)) {
					// update secondary colors
					ss.setDefaultColors(c);
					shapePrimarySelectedColor.setSelectedColor(ss.getShapePrimarySelectedColor());
					shapeSecondarySelectedColor.setSelectedColor(ss.getShapeSecondarySelectedColor());
					shapeForeground.setSelectedColor(ss.getShapeForeground());
					textColor.setSelectedColor(ss.getTextColor());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
    		
    	});
		shapeForeground = new ColorControl("&Foreground Color:",styleEditors);
		shapePrimarySelectedColor = new ColorControl("&Selected Color:",styleEditors);
		shapeSecondarySelectedColor = new ColorControl("&Multi-Selected Color:",styleEditors);
		textColor = new ColorControl("&Label Color:",styleEditors);
		textFont = new FontControl("Label &Font:",styleEditors);
		
		loadStyleEditors();

        return container;
	}

	private void saveStyleEditors() {
		if (currentSelection!=null) {
			ShapeStyle ss = shapeStyles.get(currentSelection);
			ss.setShapeBackground(shapeBackground.getSelectedColor());
			ss.setShapePrimarySelectedColor(shapePrimarySelectedColor.getSelectedColor());
			ss.setShapeSecondarySelectedColor(shapeSecondarySelectedColor.getSelectedColor());
			ss.setShapeForeground(shapeForeground.getSelectedColor());
			ss.setTextFont(textFont.getSelectedFont());
			ss.setTextColor(textColor.getSelectedColor());
		}
	}
	
	private void loadStyleEditors() {
		if (shapeStyles==null) {
			shapeStyles = new LinkedHashMap<Class, ShapeStyle>();
			for (Class c : allElements) {
				ShapeStyle ss = preferences.getShapeStyle(c);
				shapeStyles.put(c, ss);
			if (debug) {
				IColorConstant foreground = ss.getShapeForeground();
				IColorConstant background = ss.getShapeBackground();
				IColorConstant textColor = ss.getTextColor();
				Font font = ss.getTextFont();
				System.out.println("\t\t<style object=\""+c.getSimpleName()+"\""+
						" foreground=\""+ShapeStyle.colorToString(foreground)+"\""+
						" background=\""+ShapeStyle.colorToString(background)+"\""+
						" textColor=\""+ShapeStyle.colorToString(textColor)+"\""+
						" font=\""+ShapeStyle.fontToString(font)+"\"/>");
				}
			}
	        elementsListViewer.setInput(shapeStyles);
	        currentSelection = null;
	        elementsListViewer.setSelection(null);
			styleEditors.setVisible(false);
		}

		IStructuredSelection sel = (IStructuredSelection)elementsListViewer.getSelection();
		if (sel!=null && sel.getFirstElement()!=null) {
			Class c = (Class)sel.getFirstElement();
			ShapeStyle ss = shapeStyles.get(c);

			shapeBackground.setSelectedColor(ss.getShapeBackground());
			shapePrimarySelectedColor.setSelectedColor(ss.getShapePrimarySelectedColor());
			shapeSecondarySelectedColor.setSelectedColor(ss.getShapeSecondarySelectedColor());
			shapeForeground.setSelectedColor(ss.getShapeForeground());
			textFont.setSelectedFont(ss.getTextFont());
			textColor.setSelectedColor(ss.getTextColor());

			boolean visible = true;
			if (FeatureMap.CONNECTORS.contains(c)) {
				visible = false;
			}
			shapeBackground.setVisible(visible);
			((GridData)shapeBackground.getLayoutData()).exclude = !visible;
			shapePrimarySelectedColor.setVisible(visible);
			((GridData)shapePrimarySelectedColor.getLayoutData()).exclude = !visible;
			shapeSecondarySelectedColor.setVisible(visible);
			((GridData)shapeSecondarySelectedColor.getLayoutData()).exclude = !visible;
			container.layout();
		}
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		try {
			preferences.restoreDefaults(false);
			shapeStyles = null;
			loadStyleEditors();
			preferences.save();
		}
		catch(Exception e) {
		}
	}

	@Override
	public boolean performOk() {
		saveStyleEditors();
		for (Entry<Class, ShapeStyle> entry : shapeStyles.entrySet()) {
			preferences.setShapeStyle(entry.getKey(), entry.getValue());
		}
		try {
			preferences.save();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return super.performOk();
	}

	public class BEListContentProvider implements IStructuredContentProvider {

		List<Class> list;
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		@Override
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput!=null) {
				list = new ArrayList<Class>();
				list.addAll( ((LinkedHashMap<Class, ShapeStyle>)newInput).keySet() );
			}
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		@Override
		public Object[] getElements(Object inputElement) {
			if (list!=null) {
				return list.toArray();
			}
			return null;
		}
		
	}

	public class BEListLabelProvider extends LabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof Class)
				return ((Class)element).getSimpleName();
			return "";
		}
	}
	
	public class BEListSelectionChangedListener implements ISelectionChangedListener {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection sel = (IStructuredSelection)elementsListViewer.getSelection();
			if (currentSelection!=null) {
				saveStyleEditors();
			}
			
			if (sel!=null && sel.getFirstElement()!=null) {
				styleEditors.setVisible(true);
				currentSelection = (Class)sel.getFirstElement();
			}
			else
				styleEditors.setVisible(false);

			loadStyleEditors();
		}
		
	}

	public class ColorControl extends Composite {
		private ColorSelector colorSelector;
	    private Label selectorLabel;
	    private List<SelectionListener> listeners;
	    
	    public ColorControl(String labelText, Composite parent) {
	    	super(parent, SWT.NONE);
	    	this.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
	    	this.setLayout(new GridLayout(2, false));

	    	selectorLabel = new Label(this, SWT.LEFT);
	    	selectorLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	    	selectorLabel.setFont(parent.getFont());
	    	selectorLabel.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                	selectorLabel = null;
                }
            });
	    	selectorLabel.setText(labelText);
	    	
	    	colorSelector = new ColorSelector(this);
	    	colorSelector.getButton().setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
	    	colorSelector.getButton().addSelectionListener( new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (listeners!=null) {
						for (SelectionListener listener : listeners)
							listener.widgetSelected(e);
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
	    		
	    	});
	    }
	    
	    public void addSelectionListener(SelectionListener listener) {
	    	if (listeners==null)
	    		listeners = new ArrayList<SelectionListener>();
	    	listeners.add(listener);
	    }
	    
	    public void removeSelectionListener(SelectionListener listener) {
	    	if (listeners==null)
	    		return;
	    	listeners.remove(listener);
	    	if (listeners.size()==0)
	    		listeners = null;
	    }

		/**
		 * @return
		 */
		public IColorConstant getSelectedColor() {
			return ShapeStyle.RGBToColor(colorSelector.getColorValue());
		}
		
		public void setSelectedColor(IColorConstant c) {
			RGB rgb = ShapeStyle.colorToRGB(c);
			colorSelector.setColorValue(rgb);
		}
	}
	
	public class FontControl extends Composite {

	    /**
	     * The change font button, or <code>null</code> if none
	     * (before creation and after disposal).
	     */
	    private Button changeFontButton = null;

	    /**
	     * Font data for the chosen font button, or <code>null</code> if none.
	     */
	    private FontData[] selectedFont;

	    /**
	     * The label that displays the selected font, or <code>null</code> if none.
	     */
	    private Label previewLabel;
	    private Label selectorLabel;

	    public FontControl(String labelText, Composite parent) {
	    	super(parent, SWT.NONE);
	    	this.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
	    	this.setLayout(new GridLayout(3, false));

	    	selectorLabel = new Label(this, SWT.LEFT);
	    	selectorLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	    	selectorLabel.setFont(parent.getFont());
	    	selectorLabel.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                	selectorLabel = null;
                }
            });
	    	selectorLabel.setText(labelText);

	    	previewLabel = new Label(this, SWT.LEFT);
	    	previewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
            previewLabel.setFont(parent.getFont());
            previewLabel.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                    previewLabel = null;
                }
            });
	    	
            changeFontButton = new Button(this, SWT.PUSH);
            changeFontButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
			changeFontButton.setText("Change");
            changeFontButton.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent event) {
                    FontDialog fontDialog = new FontDialog(changeFontButton
                            .getShell());
                    if (selectedFont != null) {
						fontDialog.setFontList(selectedFont);
					}
                    FontData font = fontDialog.open();
                    if (font != null) {
                        FontData[] oldFont = selectedFont;
                        if (oldFont == null) {
							oldFont = JFaceResources.getDefaultFont().getFontData();
						}
                        setSelectedFont(font);
//                        fireValueChanged(VALUE, oldFont[0], font);
                    }

                }
            });
            changeFontButton.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                    changeFontButton = null;
                }
            });
            changeFontButton.setFont(parent.getFont());

	    }

	    public Font getSelectedFont() {
	    	if (selectedFont!=null && selectedFont.length>0)
	    		return ShapeStyle.fontDataToFont(selectedFont[0]);
	    	return null;
	    }

	    public void setSelectedFont(Font f) {
	    	setSelectedFont(ShapeStyle.fontToFontData(f));
	    }
	    
	    public void setSelectedFont(FontData fd) {

	        FontData[] bestFont = JFaceResources.getFontRegistry().filterData(
	        		new FontData[]{fd}, previewLabel.getDisplay());

	        //if we have nothing valid do as best we can
	        if (bestFont == null) {
				bestFont = getDefaultFontData();
			}

	        //Now cache this value in the receiver
	        this.selectedFont = bestFont;

	        if (previewLabel != null) {
	            previewLabel.setText(StringConverter.asString(selectedFont[0]));
	        }
	    }

	    /**
	     * Get the system default font data.
	     * @return FontData[]
	     */
	    private FontData[] getDefaultFontData() {
	        return previewLabel.getDisplay().getSystemFont().getFontData();
	    }
	}
}
