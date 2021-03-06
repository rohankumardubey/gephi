/*
 Copyright 2008-2010 Gephi
 Authors : Eduardo Ramos <eduramiba@gmail.com>
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

package org.gephi.ui.tools.plugin.edit;

import javax.swing.SwingUtilities;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Node;
import org.gephi.tools.api.EditWindowController;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;

/**
 * Implementation of EditWindowController interface of Tools API.
 *
 * @author Eduardo Ramos
 */
@ServiceProvider(service = EditWindowController.class)
public class EditWindowControllerImpl implements EditWindowController {

    public EditToolTopComponent findInstance() {
        return (EditToolTopComponent) WindowManager.getDefault().findTopComponent("EditToolTopComponent");
    }

    private void runAction(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            SwingUtilities.invokeLater(runnable);
        }
    }

    @Override
    public void openEditWindow() {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.open();
                topComponent.requestActive();
            }
        });

    }

    @Override
    public void closeEditWindow() {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.disableEdit();
                topComponent.close();
            }
        });
    }

    @Override
    public boolean isOpen() {
        IsOpenRunnable runnable = new IsOpenRunnable();
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(runnable);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return runnable.open;
    }

    @Override
    public void editNode(final Node node) {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.editNode(node);
            }
        });
    }

    @Override
    public void editNodes(final Node[] nodes) {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.editNodes(nodes);
            }
        });
    }

    @Override
    public void editEdge(final Edge edge) {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.editEdge(edge);
            }
        });
    }

    @Override
    public void editEdges(final Edge[] edges) {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.editEdges(edges);
            }
        });
    }

    @Override
    public void disableEdit() {
        runAction(new Runnable() {

            @Override
            public void run() {
                EditToolTopComponent topComponent = findInstance();
                topComponent.disableEdit();
            }
        });
    }

    class IsOpenRunnable implements Runnable {

        boolean open = false;

        @Override
        public void run() {
            EditToolTopComponent topComponent = findInstance();
            open = topComponent != null && topComponent.isOpened();
        }
    }
}
