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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * A cross reference adapter for resolving virtual opposite references.
 * 
 * It observes a set of references and is able to construct opposite references for these references.
 * @author Henning Heitkoetter
 *
 */
public class Bpmn2OppositeReferenceAdapter extends ECrossReferenceAdapter {
    public static final Map<EReference, EReference> DEFAULT_OBSERVED_REFERENCES;
    static {
        DEFAULT_OBSERVED_REFERENCES = new HashMap<EReference, EReference>();
        DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.FLOW_ELEMENT__CATEGORY_VALUE_REF,
                Bpmn2Package.Literals.CATEGORY_VALUE__CATEGORIZED_FLOW_ELEMENTS);
        DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.CONVERSATION_LINK__SOURCE_REF,
                Bpmn2Package.Literals.INTERACTION_NODE__OUTGOING_CONVERSATION_LINKS);
        DEFAULT_OBSERVED_REFERENCES.put(Bpmn2Package.Literals.CONVERSATION_LINK__TARGET_REF,
                Bpmn2Package.Literals.INTERACTION_NODE__INCOMING_CONVERSATION_LINKS);
    }

    /**
     * A map from references that will be observed by this adapter to their (virtual) opposite reference.
     */
    protected Map<EReference, EReference> observedRefToOpposite = new HashMap<EReference, EReference>();

    public Bpmn2OppositeReferenceAdapter(Map<EReference, EReference> observedRefToOpposite) {
        super();
        this.observedRefToOpposite.putAll(observedRefToOpposite);
    }

    public Bpmn2OppositeReferenceAdapter() {
        this(DEFAULT_OBSERVED_REFERENCES);
    }

    public EReference putObservedRefToOpposite(EReference key, EReference value) {
        return observedRefToOpposite.put(key, value);
    }

    public EReference removeObservedRef(Object key) {
        return observedRefToOpposite.remove(key);
    }

    /**
     * Returns true if the reference is being watched by this adapter.
     */
    @Override
    protected boolean isIncluded(EReference eReference) {
        return observedRefToOpposite.containsKey(eReference);
    }

    /**
     * Returns a list that holds the opposite elements of the given reference for the given owner.
     * The opposite elements are those of type E that have the reference to owner.
     * 
     * The collection corresponding to opposite in the following picture is returned,
     * for given owner and reference.
     * <pre>
     *    <b>opposite</b>            reference
     *  E ----------------------------- owner
     *  </pre>
     *  
     *  reference has to be a key of the map observedRefToOpposite.
     * @param <E>
     * @param <E> The type of the elements in the collection.
     * @param dataClass The class of the elements in the collection.
     * @param owner The object whose list is retrieved.
     * @param reference The reference whose opposite reference is retrieved.
     * @return The opposite of reference for owner.
     */
    public <E> List<E> getOppositeList(Class<E> dataClass, InternalEObject owner,
            EReference reference) {
        EReference opposite = observedRefToOpposite.get(reference);
        if (opposite == null)
            throw new IllegalArgumentException("This reference is not observed by this adapter: "
                    + reference.toString());

        List<E> result = new BasicInternalEList<E>(dataClass);

        for (Setting cur : getNonNavigableInverseReferences(owner, false)) {
            if (cur.getEStructuralFeature().equals(reference))
                result.add(dataClass.cast(cur.getEObject()));
        }

        return result;
    }
}
