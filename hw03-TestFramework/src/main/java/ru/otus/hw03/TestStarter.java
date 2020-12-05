package ru.otus.hw03;

import ru.otus.hw03.DIYTestFramework.DIYTestFramework;
import ru.otus.hw03.testedClasses.TestedClass;

public class TestStarter {
    public static void main(String[] args) {
        var testedClass = TestedClass.class;
        var testingFrame = new DIYTestFramework<TestedClass>();
        testingFrame.processTest(testedClass);

    }
}
