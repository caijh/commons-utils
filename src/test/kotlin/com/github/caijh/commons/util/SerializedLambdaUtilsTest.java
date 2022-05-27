package com.github.caijh.commons.util;

import com.github.caijh.commons.util.lambda.PropertyResolver;
import com.github.caijh.commons.util.lambda.SerializedLambda;
import com.github.caijh.commons.util.lambda.SerializedLambdaUtils;
import com.github.caijh.commons.util.reflection.property.PropertyNamer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializedLambdaUtilsTest {

    @Test
    public void resolve() {
        SerializedLambda lambda = SerializedLambdaUtils.resolve(Person::getName);
        assertEquals("getName", lambda.getImplMethodName());

        String field = PropertyNamer.methodToProperty(lambda.getImplMethodName());
        assertEquals("name", field);

        field = PropertyResolver.methodToProperty(Person::getName);
        assertEquals("name", field);
    }

}
