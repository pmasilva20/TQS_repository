package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {

    private ArrayList<T> stack;
    private int limit;

    public TqsStack() {
        this.limit = Integer.MAX_VALUE;
        this.stack = new ArrayList<>();
    }
    public TqsStack(int limit) {
        this.limit = limit;
        this.stack = new ArrayList<>();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public int size(){
        return stack.size();
    }

    public void push(T element) {
        if(this.stack.size() < limit){
            stack.add(element);
        }
        else{
            throw new IllegalStateException();
        }

    }
    public T pop() {
        if(this.stack.size() == 0){
            throw new NoSuchElementException();
        }
        else{
            return stack.remove(stack.size()-1);
        }
    }

    public T peek() {
        if(stack.size() == 0){
            throw new NoSuchElementException();
        }
        else{
            return stack.get(stack.size()-1);
        }
    }
}
