package com.javagda19.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.beans.BeanUtils.copyProperties;

public abstract class ModelUtility {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            if (descriptor.getPropertyType() == null) {
                emptyNames.add(descriptor.getName());
                continue;
            }
            Object srcValue = beanWrapper.getPropertyValue(descriptor.getName());
            if (srcValue == null) emptyNames.add(descriptor.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyNonNullProperties(Object source, Object target) {
        String[] emptyList = getNullPropertyNames(source);
        copyProperties(source, target, emptyList);
    }
}
