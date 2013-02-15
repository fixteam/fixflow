package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

public abstract class SvgShapeBaseTo extends SvgBaseTo {
	
	

	protected static final float HEIGHT_EDEFAULT = 0.0F;
	
	/**
	 * 高度
	 */
	protected float height = HEIGHT_EDEFAULT;


	
	
	protected static final float WIDTH_EDEFAULT = 0.0F;
	
	/**
	 * 宽度
	 */
	protected float width = WIDTH_EDEFAULT;


	
	
	
	protected static final float X_EDEFAULT = 0.0F;

	/**
	 * 左的距离
	 */
	protected float x = X_EDEFAULT;


	
	
	
	protected static final float Y_EDEFAULT = 0.0F;

	/**
	 * 距上的距离
	 */
	protected float y = Y_EDEFAULT;


	
	
	/**
	 *  节点的文本
	 */
	protected String label;
	
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float newHeight) {
		this.height = newHeight;
	}

	public float getWidth() {
		return this.width;
	}

	public void setWidth(float newWidth) {
		this.width = newWidth;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float newX) {
		this.x = newX;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float newY) {
		this.y = newY;
	}

}
