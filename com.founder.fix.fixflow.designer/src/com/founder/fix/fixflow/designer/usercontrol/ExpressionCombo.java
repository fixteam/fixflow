package com.founder.fix.fixflow.designer.usercontrol;

import java.util.List;


import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;


public class ExpressionCombo extends Combo {
	
	protected ExpressionTo expressionTo;
	protected ExpressionComboViewer expressionComboViewer;
	

	public ExpressionComboViewer getExpressionComboViewer() {
		return expressionComboViewer;
	}

	public void setExpressionComboViewer(ExpressionComboViewer expressionComboViewer) {
		this.expressionComboViewer = expressionComboViewer;
	}

	public ExpressionCombo(Composite parent, int style) {
		
		super(parent, style);
	}
	
	@Override
	protected void checkSubclass()
	{
		
	}
	@Override
	public void setText (String string)
	{
		
	
		super.setText("表达式名称:  "+string);
	}

	
	public ExpressionTo getExpressionTo() {
		return expressionTo;
	}

	public void setExpressionTo(ExpressionTo expressionTo) {
		
		if(expressionTo==null)
		{
			this.expressionTo=expressionTo;
			this.setText("");
			@SuppressWarnings("unchecked")
			List<ExpressionTo> expressionToList=(List<ExpressionTo>)expressionComboViewer.getInput();
			expressionToList.get(1).setName("");
			expressionToList.get(1).setExpressionText("");
			expressionComboViewer.setSelection(null);
			expressionComboViewer.refresh();
			//this.select(1);
		}
		else {
			
			
				this.expressionTo=expressionTo;
				this.setText(expressionTo.getName());
				@SuppressWarnings("unchecked")
				List<ExpressionTo> expressionToList=(List<ExpressionTo>)expressionComboViewer.getInput();
				expressionToList.get(1).setName(expressionTo.getName());
				expressionToList.get(1).setExpressionText(expressionTo.getExpressionText());
				
				expressionComboViewer.refresh();
				this.select(1);
			
			
			
		}
	}
	
	public void cleanData()
	{
		@SuppressWarnings("unchecked")
		List<ExpressionTo> expressionToList=(List<ExpressionTo>)expressionComboViewer.getInput();
		expressionToList.get(1).setName(null);
		expressionToList.get(1).setExpressionText(null);
		expressionComboViewer.refresh();
		this.select(1);
	}

}
