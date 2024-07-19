package com.y.gui.common.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 对比两个对象属性值差异
 */
public class FieldsChangeUtil {
    public static Map<String, String> compareFieldsVal(Object o1, Object o2) {
        Map<String, String> map = new HashMap<>();
        if (o1 == o2) {
            return map;
        }

        Class<?> obj1Class = o1.getClass();
        Class<?> obj2Class = o2.getClass();
        if (!obj1Class.getName().equals(obj2Class.getName())) {
            return map;
        }

        Method[] methods = obj1Class.getMethods();
        try {
            for (Method method : methods) {
                String methodName = method.getName();
                if (!methodName.startsWith("get")) {
                    continue;
                }

                Object invoke1 = method.invoke(o1);
                Object invoke2 = obj2Class.getMethod(methodName).invoke(o2);
                if (invoke1 == invoke2 || invoke1.equals(invoke2)) {
                    continue;
                }
                String key = methodName.replace("get", "");
                key = Character.toLowerCase(key.charAt(0)) + key.substring(1);
                map.put(key, invoke1 + " -> " + invoke2);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
