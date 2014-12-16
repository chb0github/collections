package org.bongiorno.misc.utils.functions;

import javax.annotation.Nullable;

public interface Function<I, O>  {


    O apply(@Nullable I input);
}