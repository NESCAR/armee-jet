package icu.nescar.armee.jet.utils;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.annotation.DebugOnly;

/**
 * @author hylexus
 * Created At 2019-08-28 10:07 下午
 */
public class CommonUtils {

    public static boolean isBuiltinComponent(Class<?> userClass) {
        return userClass.isAnnotationPresent(BuiltinComponent.class)
                || icu.nescar.armee.jet.common.BuiltinComponent.class.isAssignableFrom(userClass);
    }


    public static boolean isDeprecatedClass(Class<?> userClass) {
        return userClass.isAnnotationPresent(Deprecated.class)
                || userClass.isAnnotationPresent(DebugOnly.class)
                || icu.nescar.armee.jet.common.DebugOnly.class.isAssignableFrom(userClass);
    }

    public static String shortClassName(Class<?> cls) {
        String[] arr = cls.getName().split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length - 1; i++) {
            stringBuilder.append(arr[i].charAt(0)).append(".");
        }
        return stringBuilder.append(arr[arr.length - 1]).toString();
    }
}
