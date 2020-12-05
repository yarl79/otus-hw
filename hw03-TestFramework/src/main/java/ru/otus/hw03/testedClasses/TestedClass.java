package ru.otus.hw03.testedClasses;

import ru.otus.hw03.annotations.After;
import ru.otus.hw03.annotations.Before;
import ru.otus.hw03.annotations.Test;

public class TestedClass {

    @Before
    public void Setup() {
        System.out.println("This method is calling before tested method. Class code: " +
                Integer.toHexString(hashCode()));
    }

    @Test
    public void testedMethod1() {
        System.out.println("Tested method № 1 is processing. Class code: " +
        Integer.toHexString(hashCode()));
    }

    @Test
    public void testedMethod2() {
        System.out.println("Tested method № 2 is processing. Class code: " +
                        Integer.toHexString(hashCode()));
    }

    @Test
    public void testedMethod3() {
        throw new RuntimeException("Test 3 is failed. Class code: " +
                                        Integer.toHexString(hashCode()));
    }

    @After
    public void postProcessing() {
        System.out.println("This method is running after tested method. Class code:" +
                                                        Integer.toHexString(hashCode()));
    }


}
