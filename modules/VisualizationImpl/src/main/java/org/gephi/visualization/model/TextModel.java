/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gephi.visualization.model;

import org.gephi.graph.api.ElementProperties;

/**
 * @author mbastian
 */
public interface TextModel {

    public boolean hasCustomTextColor();

    public float getTextWidth();

    public float getTextHeight();

    public String getText();

    public void setText(String text);

    public float getTextSize();

    public float getTextR();

    public float getTextG();

    public float getTextB();

    public float getTextAlpha();

    public boolean isTextVisible();

    public ElementProperties getElementProperties();
}
