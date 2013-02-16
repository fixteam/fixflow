/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.core.preferences;

import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.ColorUtil;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Bob Brodt
 *
 */
public class ShapeStyle {

	public static IColorConstant DEFAULT_COLOR = new ColorConstant(212, 231, 248);
	public static String DEFAULT_FONT_STRING = "arial,10,-,-";
	IColorConstant shapeBackground;
	IColorConstant shapePrimarySelectedColor;
	IColorConstant shapeSecondarySelectedColor;
	IColorConstant shapeForeground;
	Font textFont;
	IColorConstant textColor;
	boolean dirty;

	public ShapeStyle() {
		setDefaultColors(DEFAULT_COLOR);
		textFont = stringToFont(DEFAULT_FONT_STRING);
	}

	public ShapeStyle(ShapeStyle other) {
		this(encode(other));
	}

	public ShapeStyle(String foreground, String background, String textColor, String font) {
		// only background color is required to set up default color scheme
		shapeBackground = stringToColor(background);
		setDefaultColors(shapeBackground);
		
		// optional:
		if (foreground!=null && !foreground.isEmpty())
			shapeForeground = stringToColor(foreground);
		if (textColor!=null && !textColor.isEmpty())
			this.textColor = stringToColor(textColor);
		if (font==null || font.isEmpty())
			font = DEFAULT_FONT_STRING;
		textFont = stringToFont(font);
	}
	
	protected ShapeStyle(String s) {
		String[] a = s.trim().split(";");
		shapeBackground = stringToColor(a[0]);
		shapePrimarySelectedColor = stringToColor(a[1]);
		shapeSecondarySelectedColor = stringToColor(a[2]);
		shapeForeground = stringToColor(a[3]);
		textFont = stringToFont(a[4]);
		textColor = stringToColor(a[5]);
	}
	
	public void setDefaultColors(IColorConstant defaultColor) {
		setShapeBackground(defaultColor);
		setShapePrimarySelectedColor(StyleUtil.shiftColor(defaultColor, 32));
		setShapeSecondarySelectedColor(StyleUtil.shiftColor(defaultColor, -32));
		setShapeForeground(StyleUtil.shiftColor(defaultColor, -128));
		setTextColor(StyleUtil.shiftColor(defaultColor, -128));
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public IColorConstant getShapeBackground() {
		return shapeBackground;
	}

	public void setShapeBackground(IColorConstant shapeDefaultColor) {
		if (!compare(this.shapeBackground, shapeDefaultColor)) {
			this.shapeBackground = shapeDefaultColor;
			setDirty(true);
		}
	}

	public IColorConstant getShapePrimarySelectedColor() {
		return shapePrimarySelectedColor;
	}

	public void setShapePrimarySelectedColor(IColorConstant shapePrimarySelectedColor) {
		if (!compare(this.shapePrimarySelectedColor, shapePrimarySelectedColor)) {
			this.shapePrimarySelectedColor = shapePrimarySelectedColor;
			setDirty(true);
		}
	}

	public IColorConstant getShapeSecondarySelectedColor() {
		return shapeSecondarySelectedColor;
	}

	public void setShapeSecondarySelectedColor(IColorConstant shapeSecondarySelectedColor) {
		if (!compare(this.shapeSecondarySelectedColor, shapeSecondarySelectedColor)) {
			this.shapeSecondarySelectedColor = shapeSecondarySelectedColor;
			setDirty(true);
		}
	}

	public IColorConstant getShapeForeground() {
		return shapeForeground;
	}

	public void setShapeForeground(IColorConstant shapeBorderColor) {
		if (!compare(this.shapeForeground, shapeBorderColor)) {
			this.shapeForeground = shapeBorderColor;
			setDirty(true);
		}
	}

	public Font getTextFont() {
		return textFont;
	}

	public void setTextFont(Font textFont) {
		if (!compare(this.textFont, textFont)) {
			this.textFont = textFont;
			setDirty(true);
		}
	}

	public IColorConstant getTextColor() {
		return textColor;
	}

	public void setTextColor(IColorConstant textColor) {
		if (!compare(this.textColor, textColor)) {
			this.textColor = textColor;
			setDirty(true);
		}
	}
	
	public static String colorToString(IColorConstant c) {
		return new String(
				String.format("%02X",c.getRed()) +
				String.format("%02X",c.getGreen()) +
				String.format("%02X",c.getBlue())
				);
	}
	
	public static IColorConstant stringToColor(String s) {
		if (s.contains(",")) {
			String[] a = s.split(",");
			int r = Integer.parseInt(a[0]);
			int g = Integer.parseInt(a[1]);
			int b = Integer.parseInt(a[2]);
			return new ColorConstant(r, g, b);
		}
		if (s.length()<6)
			return new ColorConstant(0,0,0);
		return new ColorConstant(
				ColorUtil.getRedFromHex(s),
				ColorUtil.getGreenFromHex(s),
				ColorUtil.getBlueFromHex(s)
				);
	}
	
	public static RGB colorToRGB(IColorConstant c) {
		return new RGB(c.getRed(),c.getGreen(),c.getBlue());
	}
	
	public static IColorConstant RGBToColor(RGB rgb) {
		return new ColorConstant(rgb.red, rgb.green, rgb.blue);
	}

	public static String fontToString(Font f) {
		return new String(
				f.getName() + "," +
				f.getSize() + "," +
				(f.isItalic() ? "I" : "-") + "," +
				(f.isBold() ? "B" : "-")
				);
	}
	
	public static Font stringToFont(String s) {
		String[] a = s.split(",");
		Font f = StylesFactory.eINSTANCE.createFont();
		f.eSet(StylesPackage.eINSTANCE.getFont_Name(), a[0]);
		f.eSet(StylesPackage.eINSTANCE.getFont_Size(), Integer.valueOf(a[1]));
		f.eSet(StylesPackage.eINSTANCE.getFont_Italic(), a[2].equals("I"));
		f.eSet(StylesPackage.eINSTANCE.getFont_Bold(), a[3].equals("B"));
		return f;
	}

	public static FontData fontToFontData(Font f) {
		int style = 0;
		if (f.isItalic())
			style |= SWT.ITALIC;
		if (f.isBold())
			style |= SWT.BOLD;
		return new FontData(f.getName(), f.getSize(), style);
	}
	
	public static Font fontDataToFont(FontData fd) {
		Font f = StylesFactory.eINSTANCE.createFont();
		f.eSet(StylesPackage.eINSTANCE.getFont_Name(),fd.getName());
		f.eSet(StylesPackage.eINSTANCE.getFont_Size(), fd.getHeight());
		f.eSet(StylesPackage.eINSTANCE.getFont_Italic(), (fd.getStyle() & SWT.ITALIC)!=0);
		f.eSet(StylesPackage.eINSTANCE.getFont_Bold(), (fd.getStyle() & SWT.BOLD)!=0);
		return f;
	}
	
	public static String encode(ShapeStyle sp) {
		if (sp==null)
			return encode(new ShapeStyle());
		return new String(
				colorToString(sp.shapeBackground) + ";" +
				colorToString(sp.shapePrimarySelectedColor) + ";" +
				colorToString(sp.shapeSecondarySelectedColor) + ";" +
				colorToString(sp.shapeForeground) + ";" +
				fontToString(sp.textFont) + ";" +
				colorToString(sp.textColor)
				);
	}
	
	public static ShapeStyle decode(String s) {
		if (s==null || s.trim().split(";").length!=6)
			return new ShapeStyle();
		return new ShapeStyle(s);
	}
	
	public static boolean compare(IColorConstant c1, IColorConstant c2) {
		if (c1==c2)
			return true;
		if (c1==null || c2==null)
			return false;
		return c1.getRed() == c2.getRed() &&
				c1.getGreen() == c2.getGreen() &&
				c1.getBlue() == c2.getBlue();
	}
	
	public static boolean compare(Font f1, Font f2) {
		String s1 = fontToString(f1);
		String s2 = fontToString(f2);
		return s1.equals(s2);
	}

	public static boolean compare(ShapeStyle s1, ShapeStyle s2) {
		return
				compare(s1.shapeBackground, s2.shapeBackground) ||
				compare(s1.shapePrimarySelectedColor, s2.shapePrimarySelectedColor) ||
				compare(s1.shapeSecondarySelectedColor, s2.shapeSecondarySelectedColor) ||
				compare(s1.shapeForeground, s2.shapeForeground) ||
				compare(s1.textFont, s2.textFont) ||
				compare(s1.textColor, s2.textColor);
	}
}
