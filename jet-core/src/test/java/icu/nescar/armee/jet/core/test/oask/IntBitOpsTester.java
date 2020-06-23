package icu.nescar.armee.jet.core.test.oask;

import io.github.hylexus.oaks.utils.IntBitOps;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Charle Song
 * @date 2020-6-23
 */
public class IntBitOpsTester {
    @Test
    public void testStaticFunc () {
        byte[] b = {1, 2, 3, 4, 5, 6};
        System.out.println("byte array : " + Arrays.toString(b));
        int intVar = IntBitOps.intFromBytes(b, 0, 2);
        System.out.println("intFromBytes[0] : " + intVar);
    }
}
