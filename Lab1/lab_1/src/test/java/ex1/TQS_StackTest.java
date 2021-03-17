package ex1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.NoSuchElementException;


public class TQS_StackTest
{
    private TQS_Stack<String> newStack;
    private TQS_Stack<String> halfStack;

    @BeforeEach
    public void setUp(){
        newStack = new TQS_Stack<String>();

        halfStack = new TQS_Stack<String>();

        for(int i =0; i<10 ; i++){
            halfStack.push("hello");
        }
    }

    @DisplayName("a) A stack is empty on construction.")
    @Test
    public void isEmpty()
    {
        assertTrue( newStack.isEmpty() );
    }

    @DisplayName("b) A stack has size 0 on construction.")
    @Test
    public void sizeConstruction(){
        assertEquals( 0, newStack.size() );
    }

    @DisplayName("c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    public void size(){
        assertTrue(!halfStack.isEmpty());
        assertEquals( 10, halfStack.size() );
    }

    @DisplayName("d) If one pushes x then pops, the value popped is x.")
    @Test
    public void push_then_pop(){

        halfStack.push("no");
        String x;

        assertEquals( "no", halfStack.pop() );
    }

    @DisplayName("e) If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    public void push_then_peek(){

        halfStack.push("no");
        String x;
        int size = halfStack.size();
        assertEquals( "no", halfStack.peek() );
        assertEquals( size, halfStack.size() );
    }

    @DisplayName("f) If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    public void empty_after_pops(){

        for(int i =0; i<10 ; i++){
            halfStack.pop();
        }
        assertTrue( halfStack.isEmpty() );
        assertEquals( 0, halfStack.size() );
    }

    @DisplayName("g) Popping from an empty stack does throw a NoSuchElementException")
    @Test
    public void pop_exception(){

        assertThrows( NoSuchElementException.class,()->{newStack.pop();});
    }

    @DisplayName("h) Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    public void peek_exception(){

        assertThrows(NoSuchElementException.class,()->{newStack.peek();});
    }

}