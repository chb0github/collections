package org.bongiorno.collections;

import java.util.HashMap;

/**
 * @author chribong
 */
public class ImprovedMap<K,V> extends QuickMap<K,V> {
    public ImprovedMap() {
        super(new HashMap<>());
    }
}
