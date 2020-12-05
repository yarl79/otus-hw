package ru.otus.hw03.DIYTestFramework;


import ru.otus.hw03.annotations.After;
import ru.otus.hw03.annotations.Before;
import ru.otus.hw03.annotations.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DIYTestFramework<T> {

    int testMethodcount;
    int testFailedcount;

    public void processTest(Class<T> testedClass) {
        if (!ifClassMethodsHasCertainAnnotation(testedClass, Test.class)) return;
        var testedMethods = getMethodsWithCertainAnnotation(testedClass, Test.class);
        testMethodcount = testedMethods.size();
        var beforeMethod = getMethodWithCertainAnnotation(testedClass, Before.class);
        var afterMethod = getMethodWithCertainAnnotation(testedClass, After.class);

        System.out.println("Starting test");

        T testedInstance;
        try {
            for (Method method : testedMethods) {
                testedInstance = createTestedInstance(testedClass);
                if (beforeMethod.isPresent())
                    beforeMethod.get().invoke(testedInstance);
                try {
                    method.invoke(testedInstance);
                } catch (InvocationTargetException e) {
                    System.out.println("Test failed whith message: \"" + e.getCause().getMessage() + "\"");
                    testFailedcount++;
                }
                if (afterMethod.isPresent())
                    afterMethod.get().invoke(testedInstance);
                System.out.println("--------------------------------------");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Processed tests count is " + testMethodcount);
        System.out.println("Failed tests count is " + testFailedcount);
        System.out.println("Succeeded test count is " + (testMethodcount - testFailedcount));
    }

    private T createTestedInstance(Class<T> testedClass) {
        Constructor<T> testInstanceCtor = null;
        try {
            testInstanceCtor = testedClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        T testInstance = null;
        try {
            assert testInstanceCtor != null;
            testInstance = testInstanceCtor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return testInstance;
    }

    private <A extends Annotation> List<Method> getMethodsWithCertainAnnotation(Class<T> testedClass,
                                                                                Class<A> annotationClass) {
        return Arrays.stream(testedClass.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }

    private <A extends Annotation> Optional<Method> getMethodWithCertainAnnotation(Class<T> testedClass,
                                                                                   Class<A> annotationClass) {
        return Arrays.stream(testedClass.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(annotationClass))
                .findFirst();
    }

    private <A extends Annotation> boolean ifClassMethodsHasCertainAnnotation(Class<T> testedClass, Class<A> annotationClass) {
        var methodCount = Arrays.stream(testedClass.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(annotationClass))
                .count();
        return methodCount > 0;
    }

}

