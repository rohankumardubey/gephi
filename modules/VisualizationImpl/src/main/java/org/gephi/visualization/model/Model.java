package org.gephi.visualization.model;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import org.gephi.visualization.VizModel;

/**
 * @author mbastian
 */
public interface Model {

    public void display(GL2 gl, GLU glu, VizModel model);

    public boolean isSelected();

    public void setSelected(boolean selected);
}
