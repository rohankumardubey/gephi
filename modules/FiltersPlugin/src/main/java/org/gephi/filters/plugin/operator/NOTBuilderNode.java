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

package org.gephi.filters.plugin.operator;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import org.gephi.filters.spi.Category;
import org.gephi.filters.spi.Filter;
import org.gephi.filters.spi.FilterBuilder;
import org.gephi.filters.spi.FilterProperty;
import org.gephi.filters.spi.NodeFilter;
import org.gephi.filters.spi.Operator;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Subgraph;
import org.gephi.project.api.Workspace;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 * @author Mathieu Bastian
 */
@ServiceProvider(service = FilterBuilder.class)
public class NOTBuilderNode implements FilterBuilder {

    @Override
    public Category getCategory() {
        return new Category(NbBundle.getMessage(NOTBuilderNode.class, "Operator.category"));
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(NOTBuilderNode.class, "NOTBuilderNode.name");
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getDescription() {
        return NbBundle.getMessage(NOTBuilderNode.class, "NOTBuilderNode.description");
    }

    @Override
    public Filter getFilter(Workspace workspace) {
        return new NOTOperatorNode();
    }

    @Override
    public JPanel getPanel(Filter filter) {
        return null;
    }

    @Override
    public void destroy(Filter filter) {
    }

    public static class NOTOperatorNode implements Operator {

        @Override
        public int getInputCount() {
            return 1;
        }

        @Override
        public String getName() {
            return NbBundle.getMessage(NOTBuilderNode.class, "NOTBuilderNode.name");
        }

        @Override
        public FilterProperty[] getProperties() {
            return null;
        }

        @Override
        public Graph filter(Subgraph[] graphs) {
            if (graphs.length > 1) {
                throw new IllegalArgumentException("Not Filter accepts a single graph in parameter");
            }
            Graph graph = graphs[0];
            Graph mainGraph = graph.getView().getGraphModel().getGraph();
            for (Node n : mainGraph.getNodes().toArray()) {
                if (!graph.contains(n)) {
                    //The node n is not in graph
                    graph.addNode(n);
                } else {
                    //The node n is in graph
                    graph.removeNode(n);
                }
            }

            for (Edge e : mainGraph.getEdges()) {
                Node source = e.getSource();
                Node target = e.getTarget();
                if (graph.contains(source) && graph.contains(target)) {
                    Edge edgeInGraph = graph.getEdge(source, target, e.getType());
                    if (edgeInGraph == null) {
                        graph.addEdge(e);
                    }
                }
            }

            return graph;
        }

        @Override
        public Graph filter(Graph graph, Filter[] filters) {
            if (filters.length > 1) {
                throw new IllegalArgumentException("Not Filter accepts a single filter in parameter");
            }
            Filter filter = filters[0];
            if (filter instanceof NodeFilter && ((NodeFilter) filter).init(graph)) {
                List<Node> nodeToRemove = new ArrayList<>();
                NodeFilter nodeFilter = (NodeFilter) filter;
                for (Node n : graph.getNodes()) {
                    if (nodeFilter.evaluate(graph, n)) {
                        nodeToRemove.add(n);
                    }
                }
                graph.removeAllNodes(nodeToRemove);
                nodeFilter.finish();
            }

            return graph;
        }
    }
}
