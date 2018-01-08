package com.example.creating_custom_annotations;

/**
 * Created by sargiskh on 12/22/2017.
 */

public class Foo {

    @Status(priority = Status.Priority.MEDIUM, author = "Sargis_Kh", completion = 0)
    public void methodOne() {
        //no code
    }

    @Status(priority = Status.Priority.HIGH, author = "Sargis Khlopuzyan", completion = 100)
    public void methodTwo() {
        //complete code
    }

    @Status2(value = 12)
    public void methodThree() {
        //complete code
    }
}
