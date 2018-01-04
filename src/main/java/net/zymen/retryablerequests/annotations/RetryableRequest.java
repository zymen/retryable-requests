package net.zymen.retryablerequests.annotations;

import net.zymen.retryablerequests.retryableby.RetryableBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RetryableRequest {

    public RetryableBy retryableBy();
}
