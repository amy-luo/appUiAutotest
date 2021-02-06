package com.mytest.function.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider implements Iterator<Object[]>{

    private final Iterator<String> testCase;
    public MyDataProvider(ArrayList<String> testCase){
        this.testCase = testCase.iterator();
    }

    public boolean hasNext() {
        return this.testCase.hasNext();
    }

    public Object[] next() {
        return new Object[]{this.testCase.next()};
    }

    public void remove() {
        this.testCase.remove();
    }
}
