package com.founder.fix.fixflow.designer.modeler.ui.common;

/*import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;*/
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

/**
 * @author wangyu
 *
 */
public class OperatorSelectionAdapter extends SelectionAdapter {

	
	private String operatorSymbol;
/*	private StyledText control;
	private IDocument document;*/
	private Text text;

	/*public OperatorSelectionAdapter(String operatorSymbol,IDocument document, StyledText control){
		super();
		this.operatorSymbol = operatorSymbol ;
		this.control = control ;
		this.document = document ;
	}*/
	
	public OperatorSelectionAdapter(String operatorSymbol,Text text){
		super();
		this.operatorSymbol = operatorSymbol ;
		this.text = text ;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		/*try {
			int offset = control.getCaretOffset();
			String before = document.get(0, offset);
			if(offset == document.get().length()){
				document.set(before+operatorSymbol);
			}else{
				String after = document.get().substring(offset, document.get().length());
				document.set(before+operatorSymbol+after);
			}
		
			control.setCaretOffset(offset+operatorSymbol.length());
			control.setFocus();
			
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}*/
		
		text.setFocus();
		if(text.getText() == null)
			text.setText("");
		StringBuffer sb = new StringBuffer();
		int offset = text.getCaretPosition();
		String before = text.getText().substring(0, offset);
		if(offset == text.getText().length()){
			sb.append(text.getText());
			sb.append(operatorSymbol);
		}else{
			String after = text.getText().substring(offset, text.getText().length());
			sb.append(before);
			sb.append(operatorSymbol);
			sb.append(after);
		}
		
		text.setText(sb.toString());
		text.setSelection(offset+operatorSymbol.length(), offset+operatorSymbol.length());
	}

	
	
}
