package icu.nescar.armee.jet.test.oask;

import io.github.hylexus.oaks.utils.BcdOps;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Charle Song
 * @date 2020-6-23
 */
public class BcdOpsTester {
    @Test
    public void testStaticFunc () {
        byte[] b = {1, 2, 3, 4, 5, 6};
        System.out.println("byte array : " + Arrays.toString(b));
        String bcdStr = BcdOps.bytes2BcdString(b, 0, b.length);
        System.out.println("bytes2BcdString : " + bcdStr);
    }
}
