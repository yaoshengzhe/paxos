package com.yaoshengzhe.paxos;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Annotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
    @BindingAnnotation
    public @interface PersistentLogInstance {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
    @BindingAnnotation
    public @interface TransportClientInstance {
    }
}
