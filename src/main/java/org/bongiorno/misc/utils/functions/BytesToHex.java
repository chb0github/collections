package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.utils.ReversibleFunction;
import org.springframework.security.crypto.codec.Hex;

import javax.annotation.Nullable;

public class BytesToHex implements ReversibleFunction<byte[], String> {

    @Override
    public byte[] reverse(@Nullable String input) {
        return Hex.decode(input);
    }

    @Override
    public String apply(@Nullable byte[] input) {
        return new String(Hex.encode(input));
    }
}
