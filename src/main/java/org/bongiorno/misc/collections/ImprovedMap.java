package org.bongiorno.misc.collections;

import java.util.HashMap;

/**
 * @author chribong
 */
public class ImprovedMap<K,V> extends QuickMap<K,V> {
    public ImprovedMap() {
        super(new HashMap<>());
    }
}
