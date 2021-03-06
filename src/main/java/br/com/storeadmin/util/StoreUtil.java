package br.com.storeadmin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StoreUtil {


    public static
    <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);

        return list;
    }
}
