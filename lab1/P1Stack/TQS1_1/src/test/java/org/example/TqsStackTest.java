package org.example;

import org.junit.Assert;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TqsStackTest {

    private TqsStack tqsStack;
    private short stackSize = 0;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.tqsStack = new TqsStack();
    }

    @org.junit.jupiter.api.Test
        //On construction
    void isEmpty() {
        assertTrue(tqsStack.isEmpty());
    }

    @org.junit.jupiter.api.Test
    //On construction
    void size() {
        assertEquals(0,tqsStack.size(),"Stack is empty");

    }

    @org.junit.jupiter.api.Test
    //After n pushes size is n
    void push() {
        for(int i = 0 ; i < 3 ; i++){
            tqsStack.push(i);
        }

        assertFalse(tqsStack.isEmpty());
        assertEquals(3, tqsStack.size());
    }

    @org.junit.jupiter.api.Test
    //Value pushed must be the same as popped
    void pop() {
        //Push element to Stack
        tqsStack.push(10);
        assertEquals(10,tqsStack.pop());
    }

    @org.junit.jupiter.api.Test
    //Check if value after push and peek is same as pushed but size remains same
    void sizePushPeek() {
        //Push element to Stack
        tqsStack.push(10);
        int currentSize = tqsStack.size();

        assertEquals(10,tqsStack.peek());
        assertEquals(currentSize,tqsStack.size());
    }
    @org.junit.jupiter.api.Test
    //Check if exception is thrown after pop of empty stack
    void popNothing() {

        assertThrows(NoSuchElementException.class, () -> {
            tqsStack.pop();
        });
    }

    @org.junit.jupiter.api.Test
    //Check if exception is thrown after peek of empty stack
    void peekNothing() {

        assertThrows(NoSuchElementException.class, () -> {
            tqsStack.peek();
        });
    }

    @org.junit.jupiter.api.Test
    //If stack is bounded it trows an exception after pushing a stack that's full
    void pushUntilFull() {
        tqsStack = new TqsStack(10);

        for(int i = 0; i < 10; i++){
            tqsStack.push(i);
        }
        assertThrows(IllegalStateException.class, () ->{
            tqsStack.push(11);
        });
    }
}