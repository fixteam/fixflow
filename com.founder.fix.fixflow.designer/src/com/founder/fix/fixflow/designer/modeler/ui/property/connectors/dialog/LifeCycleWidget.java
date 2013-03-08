package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.fixflow.designer.util.ImageUtil;

/**
 * @author wangyu
 *
 */
public class LifeCycleWidget extends FigureCanvas {

	private Image imageLifeCycle;
	private String event ;
	private int type ; 
	private Color color;
	public static final int OTHER_LIFE_CYCLE = 0 ;
	public static final int PROCESS_LIFE_CYCLE = 1;
	public static final int USERTASK_LIFE_CYCLE = 2;
	private List<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();
	private Map<String, EventCircle> eventFigures = new HashMap<String, EventCircle>();
	
	public LifeCycleWidget(Composite parent, String event,int lifeCycleType,Color backgroundColor) {
		super(parent, SWT.NONE);
		this.event = event ;
		this.type = lifeCycleType ;
		createLifecycleImage();

		
		color = new Color(getShell().getDisplay(), 42, 108, 161) ;

		IFigure figure = null;
		if (type == USERTASK_LIFE_CYCLE) {
			figure = createUserTaskLifecycleFigure();
		} else if (type == PROCESS_LIFE_CYCLE) {
			figure = createProcessLifecycleFigure();
		}else if (type == OTHER_LIFE_CYCLE) {
			figure = createOtherLifecycleFigure();
		}
		
	
		if(backgroundColor != null)
			setBackground(backgroundColor);
		
		setContents(figure);
		
		for(EventCircle eventCircle : eventFigures.values()){
			eventCircle.refresh();
		}
	}
	
	public void addSelectionListener(SelectionListener listener){
		selectionListeners.add(listener);
	}
	
	public void removeSelectionListener(SelectionListener listener){
		selectionListeners.remove(listener);
	}
	
	/**
	 * @param figure
	 * @param eventName
	 * @return the widget associated to specified Event
	 */
	private EventCircle addEvent(IFigure figure, String eventName, org.eclipse.draw2d.geometry.Point location, int width) {
		final EventCircle onEnterFile = new EventCircle(this,eventName, width);
		if (eventName.equals(event)) {
			onEnterFile.select();
		}
		onEnterFile.setForegroundColor(color);
		onEnterFile.setBackgroundColor(color);
		onEnterFile.setLocation(location);
		figure.add(onEnterFile);
		eventFigures.put(eventName, onEnterFile);
		return onEnterFile;
	}
	
	/**
	 * @return
	 */
	private IFigure createProcessLifecycleFigure() {
		IFigure figure = new ImageFigure(imageLifeCycle);
		addEvent(figure, "process-start", new org.eclipse.draw2d.geometry.Point(58, 37), 11);
		addEvent(figure, "process-abort", new org.eclipse.draw2d.geometry.Point(129, 37), 11);
		EventCircle defaultEvent = addEvent(figure, "process-end", new org.eclipse.draw2d.geometry.Point(258, 37), 11);
		if(event == null){
			defaultEvent.select();
		}
		return figure;
	}
	
	/**
	 * @return
	 */
	private IFigure createUserTaskLifecycleFigure() {
		IFigure figure = new ImageFigure(imageLifeCycle);
		addEvent(figure, "node-enter", new org.eclipse.draw2d.geometry.Point(58, 37), 11);
		addEvent(figure, "task-assign", new org.eclipse.draw2d.geometry.Point(129, 37), 11);
		EventCircle defaultEvent = addEvent(figure, "node-leave", new org.eclipse.draw2d.geometry.Point(258, 37), 11);
		if(event == null){
			defaultEvent.select();
		}
		return figure;
	}
	
	/**
	 * @return
	 */
	private IFigure createOtherLifecycleFigure() {
		IFigure figure = new ImageFigure(imageLifeCycle);
		addEvent(figure, "node-enter", new org.eclipse.draw2d.geometry.Point(45, 40), 10);
		EventCircle defaultEvent = addEvent(figure, "node-leave", new org.eclipse.draw2d.geometry.Point(226, 40), 10);
		if(event == null){
			defaultEvent.select();
		}
		return figure;
	}
	
	private void createLifecycleImage() {
		if (type == PROCESS_LIFE_CYCLE) {
			imageLifeCycle = new Image(null, new ImageData(ImageUtil.getFixFlowImage("process_lifecycle.png")));	 //$NON-NLS-1$
		} else if (type == USERTASK_LIFE_CYCLE) {
			imageLifeCycle = new Image(null, new ImageData(ImageUtil.getFixFlowImage("usertask_lifecycle.png")));	 //$NON-NLS-1$
		} else if (type == OTHER_LIFE_CYCLE) {
			imageLifeCycle = new Image(null, new ImageData(ImageUtil.getFixFlowImage("other_lifecycle.png")));	 //$NON-NLS-1$
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if(color != null && !color.isDisposed()){
			color.dispose();
		}
	}
	
	public int getWidth(){
		return imageLifeCycle.getImageData().width ;
	}
	
	public int getHeight(){
		return imageLifeCycle.getImageData().height ;
	}

	public List<SelectionListener> getSelectionListeners() {
		return selectionListeners;
	}

	public void setEvent(String event) {
		this.event = event ;
	}
	
	public String getEvent() {
		return this.event  ;
	}
	
	public void fireEventSelected(String event) {
		eventFigures.get(event).select();
	}
	
	/**
	 * Usd for Test only
	 * @return
	 */
	public Collection<EventCircle> getEventFigures() {
		return eventFigures.values();
	}
	
}
