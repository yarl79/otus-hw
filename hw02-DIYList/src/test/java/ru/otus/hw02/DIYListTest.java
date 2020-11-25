package ru.otus.hw02;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DIYListTest {
/*
    Написать свою реализацию ArrayList на основе массива.
    class DIYArrayList<T> implements List<T>{...}

    Проверить, что на ней работают методы из java.util.Collections:
    Collections.addAll(Collection<? super T> c, T... elements)
    Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
    Collections.static <T> void sort(List<T> list, Comparator<? super T> c)

    1) Проверяйте на коллекциях с 20 и больше элементами.
    2) DIYArrayList должен имплементировать ТОЛЬКО ОДИН интерфейс - List.
    3) Если метод не имплементирован, то он должен выбрасывать исключение UnsupportedOperationException.
*/

    @Test
    void shouldAddAllElementsFromOtherCollection(){
        List<Integer> testList = new DIYList();
        Integer[] expectedData = IntStream.range(1, 100)
                .boxed()
                .toArray(Integer[]::new);

        Collections.addAll(testList, expectedData);
        assertThat(testList).containsExactly(expectedData);
    }

    @Test
    void shouldCorrectCopyAllElementsFromOtherCollection(){
        List<Integer> testList = spy(new DIYList<>());

        List<Integer> expectedData = IntStream.range(1, 100)
                .peek(i -> testList.add(0))
                .boxed()
                .collect(Collectors.toList());


        Collections.copy(testList, expectedData);
        assertThat(testList).containsExactlyElementsOf(expectedData);
        verify(testList, times(expectedData.size())).set(anyInt(), anyInt());
        verify(testList, times(1)).listIterator(); // Это д.б. в ДЗ
    }

    @SuppressWarnings("all")
    @Test
    void shouldCorrectSortElements(){
        List<Integer> testList = new DIYList<>();
        List<Integer> expectedData = IntStream.range(1, 100)
                .peek(i -> testList.add(100 - i))
                .boxed()
                .collect(Collectors.toList());

        Collections.sort(testList, Comparator.naturalOrder());
        assertThat(testList).containsExactlyElementsOf(expectedData);
    }
}
