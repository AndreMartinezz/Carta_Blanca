import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;

public class CardStack {

    private Stack<Card>  stack;

    public CardStack() {
        stack = new Stack<>();
    }

    // Añadir una carta a la pila (push)
    public void push(Card card) {
        stack.push(card);
    }

    // Eliminar y devolver la carta superior de la pila (pop)
    public Card pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    // Ver la carta superior de la pila sin eliminarla (peek)
    public Card peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    // Verificar si la pila está vacía
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Card barajear (Queue<Card> deck){
        return deck.poll();
    }

    public CardStack barajearPilas (Queue<Card> deck, CardStack pila){
        if (deck.isEmpty()){
            return pila;
        }
        pila.push(deck.poll());
        return pila;
    }

    public String toString (){
        return stack.toString();

    }



    }
