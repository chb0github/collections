package org.bongiorno.misc.utils;


import org.bongiorno.misc.collections.ImprovedSet;
import org.bongiorno.misc.collections.WSCollections;

import java.beans.PropertyEditorSupport;
import java.util.Collection;
import java.util.function.Function;



/**
 * @author chribong
 */
public class CsvToSetPropertyEditor extends PropertyEditorSupport implements Function<String, ImprovedSet<String>> {

    @Override
    public ImprovedSet<String> apply(String input) {
        ImprovedSet<String> retVal = new ImprovedSet<>();

        if ((input != null) && (input.trim().length() > 0)) {
            String[] values = input.split(",");
            for (String value : values)
                retVal.add(value.trim());
        }
        return retVal;
    }

    public String getAsText() {
        Object value = getValue();
        if (!(value instanceof Collection))
            throw new IllegalArgumentException("Only a Collection may be used. Type was: " + value.getClass());

        return WSCollections.delimitedCollection((Collection) value, ",").toString();
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue(apply(text));
    }
}