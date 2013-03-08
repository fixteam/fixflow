package com.founder.fix.fixflow.designer.usercontrol;

import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.fixflow.designer.groovyeditor.GroovyExpressionDialog;
import com.founder.fix.fixflow.designer.modeler.ui.common.ExpressionDialog;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;

public class ExpressionComboViewer extends ComboViewer {

	private ListenerList expressionListeners = new ListenerList();

	public void addExpressionChangedListeners(IExpressionChangedListener listener) {
		expressionListeners.add(listener);
	}

	public ExpressionComboViewer(Composite parent) {
		this(parent, SWT.READ_ONLY | SWT.BORDER);
	}

	protected void fireExpressioChanged(final ExpressionChangedEvent event) {
		Object[] listeners = expressionListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final IExpressionChangedListener l = (IExpressionChangedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.expressionChanged(event);
				}
			});
		}
	}

	public ExpressionComboViewer(Composite parent, int style) {
		this(new ExpressionCombo(parent, style));
		this.getExpressionCombo().setExpressionComboViewer(this);
	}

	public ExpressionComboViewer(ExpressionCombo list) {
		super(list);
		this.setLabelProvider(getViwerLabelProvider());
		this.setContentProvider(getContentProvider());
		// this.setInput(expressionTos);

		this.addSelectionChangedListener(iSelectionChangedListener);
		this.setExpressionInput(DataVarUtil.getExpressionTos());
	}

	public void cleanData()
	{
		this.getExpressionCombo().cleanData();
	}
	
	public void setDefaultExpressionInput(ExpressionTo expressionTo) {

		

			this.getExpressionCombo().setExpressionTo(expressionTo);



	}

	public void setExpressionInput(List<ExpressionTo> expressionTos) {
		ExpressionTo expressionTo = new ExpressionTo();
		expressionTo.setName("编辑表达式...");
		expressionTo.setExpressionText("");
		ExpressionTo expressionToNull = new ExpressionTo();
		expressionToNull.setName("");
		expressionToNull.setExpressionText("");

		ExpressionTo expressionToTemp = new ExpressionTo();
		expressionToTemp.setName("");
		expressionToTemp.setExpressionText("");

		expressionTos.add(expressionTos.size(), expressionTo);

		expressionTos.add(0, expressionToNull);
		expressionTos.add(1, expressionToTemp);

		super.setInput(expressionTos);
	}

	ISelectionChangedListener iSelectionChangedListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			ExpressionComboViewer expressionComboViewer = (ExpressionComboViewer) event.getSource();

			// TODO Auto-generated method stub
			IStructuredSelection selection = ((IStructuredSelection) event.getSelection());
			// 获取选中的对象TO
			ExpressionTo selectionExpressionTo = (ExpressionTo) selection.getFirstElement();
			ExpressionTo nowComboExpressionTo = expressionComboViewer.getExpressionCombo().getExpressionTo();

			if (selectionExpressionTo == null)
				return;
			
			
			if(expressionComboViewer.getExpressionCombo().getSelectionIndex() == 1){
				if(selectionExpressionTo.getName().equals("")||selectionExpressionTo.getName().equals("")){
					expressionComboViewer.setSelection(null);
				}
				return ;
			}
			
			
			// 如果选中的是编辑表达式
			if (expressionComboViewer.getExpressionCombo().getSelectionIndex() == expressionComboViewer.getExpressionCombo().getItemCount() - 1) {

				// 因为选中 编辑表达式的时候 TO 为伪造的 所以要创建一个新的TO 将缓存的 临时 表达式 放到To中

				ExpressionTo newExpressionTo = new ExpressionTo();
				if (nowComboExpressionTo != null) {
					newExpressionTo.setName(nowComboExpressionTo.getName());
					newExpressionTo.setExpressionText(nowComboExpressionTo.getExpressionText());
				}
			
				if(EMFUtil.getScriptLanguage().getId().equals("Groovy")){
					GroovyExpressionDialog groovyExpressionDialog = new GroovyExpressionDialog(getCombo().getShell(), newExpressionTo);
					groovyExpressionDialog.setBlockOnOpen(true);
					if (groovyExpressionDialog != null && groovyExpressionDialog.open() == InputDialog.OK) {
						// 如果点击的是OK
					
						if(groovyExpressionDialog.getExpressionTo().getName().equals("")&&!groovyExpressionDialog.getExpressionTo().getExpressionText().equals("")){
							groovyExpressionDialog.getExpressionTo().setName(groovyExpressionDialog.getExpressionTo().getExpressionText());
						}
						expressionComboViewer.getExpressionCombo().setExpressionTo(groovyExpressionDialog.getExpressionTo());
						// 触发事件
						fireExpressioChanged(new ExpressionChangedEvent(expressionComboViewer,groovyExpressionDialog.getExpressionTo()));
						// 重新赋值表达式缓存

					} else {
						// 点击取消的话
						if (nowComboExpressionTo != null) {
							// 还回原始值
							expressionComboViewer.getExpressionCombo().setExpressionTo(nowComboExpressionTo);
							// 触发事件
							// fireExpressioChanged(new
							// ExpressionChangedEvent(ed.getExpressionTo()));
							// comboViewer.getCombo().setText(
							// expression.getName());
						} else {
							// 还回原始值
							expressionComboViewer.getExpressionCombo().setExpressionTo(null);

						}
					}
				}
				else{
					ExpressionDialog ed = new ExpressionDialog(getCombo().getShell(), newExpressionTo);
					ed.setBlockOnOpen(true);
					if (ed != null && ed.open() == InputDialog.OK) {
						// 如果点击的是OK
					
						if(ed.getExpressionTo().getName().equals("")&&!ed.getExpressionTo().getExpressionText().equals("")){
							ed.getExpressionTo().setName(ed.getExpressionTo().getExpressionText());
						}
						expressionComboViewer.getExpressionCombo().setExpressionTo(ed.getExpressionTo());
						// 触发事件
						fireExpressioChanged(new ExpressionChangedEvent(expressionComboViewer,ed.getExpressionTo()));
						// 重新赋值表达式缓存

					} else {
						// 点击取消的话
						if (nowComboExpressionTo != null) {
							// 还回原始值
							expressionComboViewer.getExpressionCombo().setExpressionTo(nowComboExpressionTo);
							// 触发事件
							// fireExpressioChanged(new
							// ExpressionChangedEvent(ed.getExpressionTo()));
							// comboViewer.getCombo().setText(
							// expression.getName());
						} else {
							// 还回原始值
							expressionComboViewer.getExpressionCombo().setExpressionTo(null);

						}
					}
				}
				
				

			} else {
				// 非编辑表达式

				if(expressionComboViewer.getExpressionCombo().getSelectionIndex() == 0)
				{
					expressionComboViewer.getExpressionCombo().setExpressionTo(null);
					// 触发事件
					fireExpressioChanged(new ExpressionChangedEvent(expressionComboViewer,null));
				}
				else{
					

					
						expressionComboViewer.getExpressionCombo().setExpressionTo(selectionExpressionTo);
						// 触发事件
						fireExpressioChanged(new ExpressionChangedEvent(expressionComboViewer,selectionExpressionTo));
					
					
				}
				
				
				// expression = FixFlowFactory.eINSTANCE.createExpression();
				// expression.setName(dataVarTo.getId());
				// expression.setValue(dataVarTo.getValue());
				// comboViewer.getCombo().setText(UiTextUtil.expressionTextName
				// + dataVarTo.getId());
			}
		}
	};

	public ExpressionCombo getExpressionCombo() {
		return (ExpressionCombo) getCombo();
	}

	/**
	 * 得到数据变量ComboViewer控件的LabelProvider
	 * 
	 * @return
	 */
	public IBaseLabelProvider getViwerLabelProvider() {
		return new ViewerLabelProvider();
	}

	/**
	 * 数据变量ComboViewer控件的ContentProvider
	 * 
	 * @return
	 */
	public IContentProvider getContentProvider() {
		return new ContentProvider();
	}

	/**
	 * 数据变量ComboViewer控件的LabelProvider
	 * 
	 * @author wangyu
	 * 
	 */
	private class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			ExpressionTo expressionTo = (ExpressionTo) element;
			return expressionTo.getName();
		}
	}

	/**
	 * 数据变量ComboViewer控件的ContentProvider
	 * 
	 * @author wangyu
	 * 
	 */
	private class ContentProvider implements IStructuredContentProvider {
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				List<ExpressionTo> list = (List<ExpressionTo>) inputElement;

				// list.get(1).setName(list.get(1).getName());
				return list.toArray();
			} else {
				return new Object[0];
			}
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

}
