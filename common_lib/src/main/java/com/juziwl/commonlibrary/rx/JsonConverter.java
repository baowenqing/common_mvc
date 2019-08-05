package com.juziwl.commonlibrary.rx;

import com.alibaba.fastjson.JSON;
import com.juziwl.commonlibrary.model.ResponseData;
import com.lzy.okgo.convert.Converter;
import com.lzy.okgo.convert.StringConvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/08/01
 * @description okgo配合RxJava需要的转换器
 */
public class JsonConverter<T> implements Converter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            String content = body.string();
            return JSON.parseObject(content, type, JsonCallBack.EMPTY_SERIALIZER_FEATURES);
        }
        return null;
    }
}
