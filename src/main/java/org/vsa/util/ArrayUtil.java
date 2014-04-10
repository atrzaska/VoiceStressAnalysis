package org.vsa.util;

import java.util.List;

/**
 * ArrayUtil
 */
public class ArrayUtil {
    /**
     * toPrimitiveArray
     * 
     * @param input
     * @return 
     */
    public static double[] toPrimitiveArray(List<Double> input) {
        // create array
        double[] output = new double[input.size()];

        // copy values
        for(int i = 0; i < input.size(); i++) {
            output[i] = input.get(i);
        }

        // return output
        return output;
    }
}
