package org.eclipse.bpmn2.modeler.core.adapters;

import java.util.List;
import java.util.Map;

/**
 * Namespace map of K,V which also holds the reverse map of V,K
 *  
 * @param <K>
 * @param <V>
 */

public interface INamespaceMap <K,V> extends Map<K,V> {
    
    /**
     * Get the entry under key V (which is the value).
     * 
     * @param key the value key
     * @return the list of prefix names 
     */
    public List<K> getReverse ( V key );
    
    
}