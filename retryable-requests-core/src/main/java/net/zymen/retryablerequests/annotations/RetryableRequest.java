package net.zymen.retryablerequests.annotations;

import net.zymen.retryablerequests.retryableby.RetryableBy;
import net.zymen.retryablerequests.retryableby.RetryableByClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RetryableRequest {

    RetryableBy retryableBy();

    Class<? extends RetryableByClass> retryableByClass() default RetryableByClass.class;
}
