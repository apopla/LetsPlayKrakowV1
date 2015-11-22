package com.example.kitowcy.letsplaykrakow.adapters;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukasz on 21.11.15.
 */
public class FilterBuilder {
    public static final int FOOD = 0;
    public static final int CULTURE = 1;
    public static final int ENTERTAINMENT = 2;
    public static final int MONUMENTS = 3;
    private Set filterList = new HashSet<>();

    public FilterBuilder() {
    }

    @SuppressWarnings("unchecked cast")
    public FilterBuilder withAll() {
        filterList.add(0);
        filterList.add(1);
        filterList.add(2);
        filterList.add(3);
        return this;
    }

    @SuppressWarnings("unchecked cast")
    public FilterBuilder with(Integer type) {
        if (type == 0 || type == 1 || type == 2 || type == 3)
            filterList.add(type);
        return this;
    }

    public FilterBuilder clear() {
        filterList.clear();
        return this;
    }

    public boolean contains(int type) {
        return filterList.contains(type);
    }
}
