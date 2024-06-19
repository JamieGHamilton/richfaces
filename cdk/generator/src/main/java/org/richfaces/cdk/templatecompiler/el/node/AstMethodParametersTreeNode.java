/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.richfaces.cdk.templatecompiler.el.node;

import java.util.ArrayList;
import java.util.List;

import org.richfaces.cdk.templatecompiler.el.ELNodeConstants;
import org.richfaces.cdk.templatecompiler.el.ELVisitor;
import org.richfaces.cdk.templatecompiler.el.ParsingException;
import org.richfaces.cdk.templatecompiler.el.types.ELType;
import org.richfaces.cdk.templatecompiler.el.types.MethodTypesWrapper;

import com.sun.el.parser.Node;

/**
 * This class extend AbstractTreeNode and wrap AstPropertySuffix node.
 *
 * @author amarkhel
 */
public class AstMethodParametersTreeNode extends AbstractTreeNode {
    public AstMethodParametersTreeNode(Node node) {
        super(node);
    }

    @Override
    public void visit(StringBuilder sb, ELVisitor visitor) throws ParsingException {

        sb.append(ELNodeConstants.LEFT_BRACKET);

        int childrenCount = getChildrenCount();
        List<ELType> argumentTypes = new ArrayList<ELType>();

        for (int k = 0; k < childrenCount; k++) {
            if (k != 0) {
                sb.append(ELNodeConstants.COMMA);
            }
            String childOutput = getChildOutput(k, visitor);
            sb.append(childOutput);
            
            argumentTypes.add(visitor.getType());
        }

        visitor.setExpressionType(new MethodTypesWrapper(argumentTypes));
        sb.append(ELNodeConstants.RIGHT_BRACKET);
    }
}
