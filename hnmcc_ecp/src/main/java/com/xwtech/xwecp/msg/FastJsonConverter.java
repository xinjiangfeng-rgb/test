package com.xwtech.xwecp.msg;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.converters.reflection.AbstractReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.mapper.Mapper;

public class FastJsonConverter extends AbstractReflectionConverter {

    private Class type;

    public FastJsonConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
        super(mapper, reflectionProvider);
    }

    public FastJsonConverter(Mapper mapper, ReflectionProvider reflectionProvider, Class type) {
        this(mapper, reflectionProvider);
        this.type = type;
    }

    public boolean canConvert(Class type) {
        return type.equals(JSONObject.class) || type.getName().equals("com.alibaba.fastjson.JSONObject");
    }
}
