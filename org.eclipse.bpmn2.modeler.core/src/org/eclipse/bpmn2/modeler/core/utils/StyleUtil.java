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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.utils;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.IGradientType;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;

public class StyleUtil {
	
	private static final String CLASS_ID = "E-CLASS";
	private static final String FILL_STYLE = "fill.style";
	
	public enum FillStyle {
		FILL_STYLE_NONE,
		FILL_STYLE_FOREGROUND,
		FILL_STYLE_BACKGROUND,
		FILL_STYLE_DEFAULT,
		FILL_STYLE_INVERT };
	
	public static final IColorConstant CLASS_FOREGROUND = new ColorConstant(116, 143, 165);
	public static final IColorConstant CLASS_BACKGROUND = new ColorConstant(220, 233, 255);
	
	public static Style getStyleForClass(Diagram diagram) {
		Style s = findStyle(diagram, CLASS_ID);
		
		if(s == null) {
			IGaService gaService = Graphiti.getGaService();
			s = gaService.createStyle(diagram, CLASS_ID);
			s.setForeground(gaService.manageColor(diagram, CLASS_FOREGROUND));
			s.setBackground(gaService.manageColor(diagram, CLASS_BACKGROUND));
			s.setLineWidth(1);
		}
		
		return s;
	}

	private static Style findStyle(StyleContainer container, String id) {
		if (container.getStyles() != null) {
			for (Style s : container.getStyles()) {
				if (s.getId().equals(id)) {
					return s;
				}
			}
		}
		return null;
	}

	public static Diagram findDiagram(GraphicsAlgorithm shape) {
		EObject container = shape.eContainer();
		while (container!=null && !(container instanceof Diagram)) {
			container = container.eContainer();
		}
		return (Diagram)container;
	}
	
	public static void setFillStyle(GraphicsAlgorithm ga, FillStyle fillStyle) {
		Graphiti.getPeService().setPropertyValue(ga, FILL_STYLE, fillStyle.toString());
	}
	
	public static void applyStyle(GraphicsAlgorithm ga, BaseElement be) {
		if (be!=null) {
			IGaService gaService = Graphiti.getGaService();
			IPeService peService = Graphiti.getPeService();

			Diagram diagram = findDiagram(ga);
			Bpmn2Preferences pref = Bpmn2Preferences.getInstance(be.eResource());
			ShapeStyle ss = pref.getShapeStyle(be);
			IColorConstant foreground = ga instanceof AbstractText ? ss.getTextColor() : ss.getShapeForeground();
			IColorConstant background = ss.getShapeBackground();

			if (peService.getPropertyValue(ga, Bpmn2Preferences.PREF_SHAPE_STYLE)==null) {
				peService.setPropertyValue(ga, Bpmn2Preferences.PREF_SHAPE_STYLE, Boolean.toString(true));
			}

			if (BusinessObjectUtil.isConnection(be.eClass().getInstanceClass())) {
				if (ga instanceof AbstractText) {
					Font f = ss.getTextFont();
					((AbstractText)ga).setFont(gaService.manageFont(diagram, f.getName(), f.getSize(), f.isItalic(), f.isBold()));
				}
				if (ga.getForeground()==null)
					ga.setForeground(gaService.manageColor(diagram, foreground));
				// by default the fill color for connection decorators is the
				// connection line foreground color
				if (ga.getBackground()==null)
					ga.setBackground(gaService.manageColor(diagram, foreground));
			}
			else {
				String id = Bpmn2Preferences.getShapeStyleId(be);
				
				// Style only used for drawing gradients
				Style s = findStyle(diagram, id);
				if(s == null) {
					s = gaService.createStyle(diagram, id);
				}
				
				if (ga instanceof AbstractText) {
					Font f = ss.getTextFont();
					((AbstractText)ga).setFont(gaService.manageFont(diagram, f.getName(), f.getSize(), f.isItalic(), f.isBold()));
					ga.setForeground(gaService.manageColor(diagram, foreground));
				}
				else {
					s.setForeground(gaService.manageColor(diagram, foreground));
					ga.setForeground(gaService.manageColor(diagram, foreground));
				}
				
				String fillStyle = peService.getPropertyValue(ga, FILL_STYLE);
				if (fillStyle==null || fillStyle.equals(FillStyle.FILL_STYLE_DEFAULT.name())) {
					// fill with gradient
					ga.setFilled(true);
					s.setFilled(true);
					AdaptedGradientColoredAreas gradient = getStyleAdaptions(be);
					gaService.setRenderingStyle(s, gradient);
					ga.setStyle(s);
				}
				else if (fillStyle.equals(FillStyle.FILL_STYLE_FOREGROUND.name())) {
					ga.setFilled(true);
					ga.setBackground(gaService.manageColor(diagram, foreground));
				}
				else if (fillStyle.equals(FillStyle.FILL_STYLE_BACKGROUND.name())) {
					ga.setFilled(true);
					ga.setBackground(gaService.manageColor(diagram, background));
				}
				else if (fillStyle.equals(FillStyle.FILL_STYLE_INVERT.name())) {
					ga.setFilled(true);
					ga.setForeground(gaService.manageColor(diagram, background));
					ga.setBackground(gaService.manageColor(diagram, foreground));
				}
				else {
					ga.setFilled(false);
					ga.setBackground(gaService.manageColor(diagram, background));
				}
			}
		}
	}

	public static AdaptedGradientColoredAreas getStyleAdaptions(BaseElement be) {
		final AdaptedGradientColoredAreas agca = StylesFactory.eINSTANCE.createAdaptedGradientColoredAreas();

		String id = Bpmn2Preferences.getShapeStyleId(be);
		agca.setDefinedStyleId(id); //STYLE_ID);
		agca.setGradientType(IGradientType.VERTICAL);
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT,
				getPreferenceDefaultAreas(be));
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getPreferencePrimarySelectedAreas(be));
		agca.getAdaptedGradientColoredAreas().add(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getPreferenceSecondarySelectedAreas(be));
		return agca;
	}
	
	public static IColorConstant shiftColor(IColorConstant c, int amount) {
		int r = c.getRed() + amount;
		int g = c.getGreen() + amount;
		int b = c.getBlue() + amount;
		if (r>255) r = 255;
		if (r<0) r = 0;
		if (g>255) g = 255;
		if (g<0) g = 0;
		if (b>255) b = 255;
		if (b<0) b = 0;
		
		return new ColorConstant(r, g, b);
	}
	
	private static GradientColoredAreas getPreferenceDefaultAreas(BaseElement be) {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		Bpmn2Preferences pref = Bpmn2Preferences.getInstance(be.eResource());
		ShapeStyle ss = pref.getShapeStyle(be);
		IColorConstant c1 = shiftColor(ss.getShapeBackground(), -8);
		IColorConstant c2 = shiftColor(ss.getShapeBackground(), 64);

		addGradientColoredArea(gcas,
				c1, 0, LocationType.LOCATION_TYPE_ABSOLUTE_START,
				c2, 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}
	private static GradientColoredAreas getPreferencePrimarySelectedAreas(BaseElement be) {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		Bpmn2Preferences pref = Bpmn2Preferences.getInstance(be.eResource());
		ShapeStyle ss = pref.getShapeStyle(be);
		IColorConstant c1 = shiftColor(ss.getShapePrimarySelectedColor(), -64);
		IColorConstant c2 = shiftColor(ss.getShapePrimarySelectedColor(), 64);

		addGradientColoredArea(gcas,
				c1, 0, LocationType.LOCATION_TYPE_ABSOLUTE_START,
				c2, 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	private static GradientColoredAreas getPreferenceSecondarySelectedAreas(BaseElement be) {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE.createGradientColoredAreas();
		gradientColoredAreas.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas.getGradientColor();

		Bpmn2Preferences pref = Bpmn2Preferences.getInstance(be.eResource());
		ShapeStyle ss = pref.getShapeStyle(be);
		IColorConstant c1 = shiftColor(ss.getShapeSecondarySelectedColor(), -64);
		IColorConstant c2 = shiftColor(ss.getShapeSecondarySelectedColor(), 64);

		addGradientColoredArea(gcas,
				c1, 0, LocationType.LOCATION_TYPE_ABSOLUTE_START,
				c2, 0, LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	private static void addGradientColoredArea(EList<GradientColoredArea> gcas,
			IColorConstant colorStart, int locationValueStart, LocationType locationTypeStart,
			IColorConstant colorEnd, int locationValueEnd, LocationType locationTypeEnd) {
		final GradientColoredArea gca = StylesFactory.eINSTANCE.createGradientColoredArea();
		gcas.add(gca);
		gca.setStart(StylesFactory.eINSTANCE.createGradientColoredLocation());
		gca.getStart().setColor(StylesFactory.eINSTANCE.createColor());
		gca.getStart().getColor().eSet(StylesPackage.eINSTANCE.getColor_Blue(), colorStart.getBlue());
		gca.getStart().getColor().eSet(StylesPackage.eINSTANCE.getColor_Green(), colorStart.getGreen());
		gca.getStart().getColor().eSet(StylesPackage.eINSTANCE.getColor_Red(), colorStart.getRed());
		gca.getStart().setLocationType(locationTypeStart);
		gca.getStart().setLocationValue(locationValueStart);
		gca.setEnd(StylesFactory.eINSTANCE.createGradientColoredLocation());
		gca.getEnd().setColor(StylesFactory.eINSTANCE.createColor());
		gca.getEnd().getColor().eSet(StylesPackage.eINSTANCE.getColor_Blue(), colorEnd.getBlue());
		gca.getEnd().getColor().eSet(StylesPackage.eINSTANCE.getColor_Green(), colorEnd.getGreen());
		gca.getEnd().getColor().eSet(StylesPackage.eINSTANCE.getColor_Red(), colorEnd.getRed());
		gca.getEnd().setLocationType(locationTypeEnd);
		gca.getEnd().setLocationValue(locationValueEnd);
	}
}
