/**
 * Copyright (c) 2010 Henning Heitkoetter.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Henning Heitkoetter - initial API and implementation
 */
package org.eclipse.bpmn2.util;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * A BPMN 2.0 enabled resource that provides BPMN2-specific features.
 * @author Henning Heitkoetter
 *
 */
public interface Bpmn2Resource extends Resource {
    /**
     * Returns the adapter for resolving opposite references attached to this resource 
     * (respectively its resource set). Every resource has its own adapter.
     * @return The adapter associated with this resource.
     */
    Bpmn2OppositeReferenceAdapter getOppositeReferenceAdapter();
}
