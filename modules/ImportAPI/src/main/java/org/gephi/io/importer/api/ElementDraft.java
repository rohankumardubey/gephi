/*
 Copyright 2008-2010 Gephi
 Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
 Website : http://www.gephi.org

 This file is part of Gephi.

 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

 Copyright 2011 Gephi Consortium. All rights reserved.

 The contents of this file are subject to the terms of either the GNU
 General Public License Version 3 only ("GPL") or the Common
 Development and Distribution License("CDDL") (collectively, the
 "License"). You may not use this file except in compliance with the
 License. You can obtain a copy of the License at
 http://gephi.org/about/legal/license-notice/
 or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
 specific language governing permissions and limitations under the
 License.  When distributing the software, include this License Header
 Notice in each file and include the License files at
 /cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
 License Header, with the fields enclosed by brackets [] replaced by
 your own identifying information:
 "Portions Copyrighted [year] [name of copyright owner]"

 If you wish your version of this file to be governed by only the CDDL
 or only the GPL Version 3, indicate your decision by adding
 "[Contributor] elects to include this software in this distribution
 under the [CDDL or GPL Version 3] license." If you do not indicate a
 single choice of license, a recipient has the option to distribute
 your version of this file under either the CDDL, the GPL Version 3 or
 to extend the choice of license to its licensees as provided above.
 However, if you add GPL Version 3 code and therefore, elected the GPL
 Version 3 license, then the option applies only if the new code is
 made subject to such option by the copyright holder.

 Contributor(s):

 Portions Copyrighted 2011 Gephi Consortium.
 */

package org.gephi.io.importer.api;

import java.awt.Color;
import org.gephi.graph.api.Interval;
import org.gephi.graph.api.types.TimeSet;

/**
 * Draft element, hosted by import containers to represent nodes or edges found
 * when importing.
 * <p>
 * The Factory sub-interface defined the methods to create new element
 * instances.
 *
 * @author Mathieu Bastian
 */
public interface ElementDraft {

    /**
     * Returns the element's id.
     * <p>
     * The element id is unique.
     *
     * @return element's id
     */
    String getId();

    /**
     * Returns the element's value for <code>key</code>.
     *
     * @param key key
     * @return value or null if not found
     */
    Object getValue(String key);

    /**
     * Returns the element's label.
     *
     * @return label or null if unset
     */
    String getLabel();

    /**
     * Sets this element's label.
     *
     * @param label label
     */
    void setLabel(String label);

    /**
     * Returns the element's color.
     *
     * @return color or null if unset
     */
    Color getColor();

    /**
     * Sets this element's color.
     *
     * @param color color
     */
    void setColor(Color color);

    /**
     * Parse and sets this element's color.
     * <p>
     * Color can be an existing Java color (e.g. yellow, blue, cyan) or an octal
     * or hexadecimal color representation (e.g. 0xFF0096, #FF0096).
     *
     * @param color color to be parsed and set
     */
    void setColor(String color);

    /**
     * Returns true if the label is visible.
     * <p>
     * Default value is true.
     *
     * @return true if label is visible, false otherwise
     */
    boolean isLabelVisible();

    /**
     * Sets whether the label is visible.
     *
     * @param labelVisible label visible flag
     */
    void setLabelVisible(boolean labelVisible);

    /**
     * Returns the label's size.
     * <p>
     * Default value is -1.
     *
     * @return label size
     */
    float getLabelSize();

    /**
     * Sets the label's size.
     *
     * @param size label size
     */
    void setLabelSize(float size);

    /**
     * Returns the label's color.
     *
     * @return label's color
     */
    Color getLabelColor();

    /**
     * Sets the label's color.
     *
     * @param color label color
     */
    void setLabelColor(Color color);

    /**
     * Parses and sets the label's color.
     * <p>
     * Color can be an existing Java color (e.g. yellow, blue, cyan) or an octal
     * or hexadecimal color representation (e.g. 0xFF0096, #FF0096).
     *
     * @param color color to be parsed and set
     */
    void setLabelColor(String color);

    /**
     * Sets the <code>value</code> for <code>key</code>.
     *
     * @param key   key
     * @param value value
     */
    void setValue(String key, Object value);

    /**
     * Sets the <code>value</code> for <code>key</code> at the given
     * <code>timestamp</code>.
     *
     * @param key       key
     * @param value     value
     * @param timestamp timestamp
     */
    void setValue(String key, Object value, double timestamp);

    /**
     * Sets the <code>value</code> for <code>key</code> at the given interval
     * <code>[start,end]</code>.
     *
     * @param key   key
     * @param value value
     * @param start interval start
     * @param end   interval end
     */
    void setValue(String key, Object value, double start, double end);

    /**
     * Sets the <code>value</code> for <code>key</code> at the given
     * <code>dateTime</code>.
     *
     * @param key      key
     * @param value    value
     * @param dateTime dateTime
     */
    void setValue(String key, Object value, String dateTime);

    /**
     * Sets the <code>value</code> for <code>key</code> at the given interval
     * <code>[startDateTime,endDateTime]</code>.
     *
     * @param key           key
     * @param value         value
     * @param startDateTime interval start datetime
     * @param endDateTime   interval end datetime
     */
    void setValue(String key, Object value, String startDateTime, String endDateTime);

    /**
     * Parses and sets the <code>value</code> for <code>key</code>.
     *
     * @param key   key
     * @param value value
     */
    void parseAndSetValue(String key, String value);

    /**
     * Parses and sets the <code>value</code> for <code>key</code> at the given
     * <code>timestamp</code>.
     *
     * @param key       key
     * @param value     value
     * @param timestamp timestamp
     */
    void parseAndSetValue(String key, String value, double timestamp);

    /**
     * Parses and sets the <code>value</code> for <code>key</code> at the given
     * interval <code>[start,end]</code>.
     *
     * @param key   key
     * @param value value
     * @param start interval start
     * @param end   interval end
     */
    void parseAndSetValue(String key, String value, double start, double end);

    /**
     * Parses and sets the <code>value</code> for <code>key</code> at the given
     * <code>dateTime</code>.
     *
     * @param key      key
     * @param value    value
     * @param dateTime dateTime
     */
    void parseAndSetValue(String key, String value, String dateTime);

    /**
     * Parses and sets the <code>value</code> for <code>key</code> at the given
     * <code>dateTime</code>.
     *
     * @param key           key
     * @param value         value
     * @param startDateTime interval start datetime
     * @param endDateTime   interval end datetime
     */
    void parseAndSetValue(String key, String value, String startDateTime, String endDateTime);

    /**
     * Parses and sets this element's color using string components.
     * <p>
     * Components should be numbers between 0 and 255.
     *
     * @param r red component as string
     * @param g green component as string
     * @param b blue component as string
     */
    void setColor(String r, String g, String b);

    /**
     * Sets this element's color using real color numbers (i.e numbers between 0
     * and 1).
     *
     * @param r red component as float
     * @param g green component as float
     * @param b blue component as float
     */
    void setColor(float r, float g, float b);

    /**
     * Sets this element's color using int color numbers (i.e numbers between 0
     * and 255).
     *
     * @param r red component as int
     * @param g green component as int
     * @param b blue component as int
     */
    void setColor(int r, int g, int b);

    /**
     * Parses and sets the label's color using string components.
     * <p>
     * Components should be numbers between 0 and 255.
     *
     * @param r red component as string
     * @param g green component as string
     * @param b blue component as string
     */
    void setLabelColor(String r, String g, String b);

    /**
     * Sets the label's color using real color numbers (i.e numbers between 0
     * and 1).
     *
     * @param r red component as float
     * @param g green component as float
     * @param b blue component as float
     */
    void setLabelColor(float r, float g, float b);

    /**
     * Sets the label's color using int color numbers (i.e numbers between 0 and
     * 255).
     *
     * @param r red component as int
     * @param g green component as int
     * @param b blue component as int
     */
    void setLabelColor(int r, int g, int b);

    void addTimestamp(double timestamp);

    void addTimestamp(String dateTime);

    void addTimestamps(String timestamps);

    void addInterval(double start, double end);

    void addInterval(String startDateTime, String endDateTime);

    void addIntervals(String intervals);

    TimeSet getTimeSet();

    Iterable<ColumnDraft> getColumns();

    Double getGraphTimestamp();

    Interval getGraphInterval();

    /**
     * Node and edge draft factory. Creates node and edge to push in the
     * container.
     */
    interface Factory {

        /**
         * Returns an empty node draft instance.
         *
         * @return an instance of <code>NodeDraft</code>
         */
        NodeDraft newNodeDraft();

        /**
         * Returns an empty node draft instance.
         *
         * @param id node id
         * @return an instance of <code>NodeDraft</code>
         */
        NodeDraft newNodeDraft(String id);

        /**
         * Returns an empty edge draft instance. Note that <b>source</b> and
         * <b>target</b> have to be set.
         *
         * @return an instance of <code>EdgeDraft</code>
         */
        EdgeDraft newEdgeDraft();

        /**
         * Returns an empty edge draft instance.
         *
         * @param id edge id
         * @return an instance of <code>EdgeDraft</code>
         */
        EdgeDraft newEdgeDraft(String id);
    }
}
