package ex1;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class TQS_Stack<T>{

    private LinkedList stack = new LinkedList<T>();

    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public int size(){
        return stack.size();
    }
    public void push(T val){
        stack.push(val);
    }
    public T peek() throws NoSuchElementException{
        if (stack.peekFirst()!=null){
            return (T) stack.peekFirst();
        }
        throw new NoSuchElementException();
    }
    public T pop()throws NoSuchElementException{
        return (T) stack.pop();
    }

}