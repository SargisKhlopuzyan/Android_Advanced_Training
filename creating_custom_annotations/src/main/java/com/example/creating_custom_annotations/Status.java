package com.example.creating_custom_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sargiskh on 12/22/2017.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Status {
    public enum Priority {LOW, MEDIUM, HIGH}
    Priority priority() default Priority.LOW;
    String author() default "Sargis";
    int completion() default 0;
}
