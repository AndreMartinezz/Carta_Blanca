import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        boolean gameOver = false;
        int origen = 0;
        int destino = 0;
        // Inicialización del juego
        Queue<Card> mazo = new LinkedList<>();
        cardStart(mazo);
        // Creación de las pilas de cartas (mesa, mazo, pilas de destino)
        CardStack[] stackPilas = new CardStack[16];
        creacionPilas(mazo, stackPilas);
        // Bucle principal del juego
        while (!gameOver){
            UI(stackPilas);
            // Permitir al jugador realizar movimientos
            System.out.println("Ejemplo[Origen: 1 y Destino: 2] moveria la ultima carta de la pila 1 a la pila 2");
            System.out.println("Origen:" );
            origen = leer.nextInt();
            System.out.println("Destino:" );
            destino = leer.nextInt();

            if(boolMov(stackPilas, origen, destino)){
                stackPilas = moverCarta(stackPilas, origen, destino);
            }else {
                System.out.println("El movimiento no es valido");
            }

            if (haGanado(stackPilas)){
                System.out.println("Felicidades has ganado");
            }
            if (haPerdido(stackPilas)){
                System.out.println("Lastima, intentalo de nuevo :(");
                gameOver = true;
            }
        }
    }
    public static boolean noEsNulo(CardStack[] stackPila, int origen) {

        if (stackPila[origen] == (null)) {
            return false; // Si se encuentra al menos un elemento no nulo, retornamos true
        }

        return true;
    }
    public static boolean boolMov (CardStack[] stacks, int origen, int destino){

        int diferencia = 0;

        if (destino>15){return false;
        }else if (noEsNulo(stacks,origen)){
            if(destino >= 0 && destino < 8) {
                if (stacks[destino].isEmpty()){
                    return true;
                }else {diferencia = stacks[destino].peek().getValue() - stacks[origen].peek().getValue();}

                if (stacks[origen].peek().getSuit().equals("P") || stacks[origen].peek().getSuit().equals("T")) {
                    if (stacks[destino].peek().getSuit().equals("C") || stacks[destino].peek().getSuit().equals("D")) {
                        if (diferencia == 1) {
                            return true;
                        }
                    }
                } else if (stacks[destino].peek().getSuit().equals("P") || stacks[destino].peek().getSuit().equals("T")) {
                    if (stacks[origen].peek().getSuit().equals("C") || stacks[origen].peek().getSuit().equals("D")) {
                        if (diferencia == 1) {
                            return true;
                        }
                    }
                }
            }
            if (destino > 7 && destino < 12){
                if (stacks[destino].isEmpty()){
                    return true;

                }else  {return false;}

            }
            if (destino >= 12 && destino < 16) {
                if (stacks[destino].isEmpty()){
                    if (stacks[origen].peek().getValue() == 1){
                        return true;
                    }
                }else if (stacks[destino].peek().getSuit().equals(stacks[origen].peek().getSuit())){
                    if ((diferencia = stacks[destino].peek().getValue()-stacks[origen].peek().getValue())<1){return true;}
                }
            }
        }
        return false;
    }



    public static CardStack[] creacionPilas (Queue<Card> mazo,CardStack[] stackPilas){
        for(int i = 0; i<stackPilas.length; i++){
            stackPilas[i] = new CardStack();
        }
        while (!mazo.isEmpty()){
            for(int i = 0; i<8; i++){
                stackPilas[i].barajearPilas(mazo, stackPilas[i]);
            }
        }
        return stackPilas;
    }
    public static void UI (CardStack[] stackPilas) {
        System.out.println("    Pilas Destino     ||     Pilas Extras");
        System.out.println(" 12   13   14   15    ||   8    9    10   11   ");
        for (int i = 12; i<16;i++) {
            System.out.print(stackPilas[i].peek() + "|");
        }
        System.out.print("  ||  ");
        for (int i = 8; i<12;i++) {
            if (i==11) {
                System.out.println(stackPilas[i].peek() + "|");
            }else{
                System.out.print(stackPilas[i].peek() + "|");
            }
        }
        for (int i = 0; i<8;i++) {
            System.out.println(i + ":" + stackPilas[i]);
        }
    }
    public static CardStack[] moverCarta (CardStack[] stacks, int origen, int destino){

        stacks[destino].push(stacks[origen].pop());

        return stacks;
    }
    public static boolean haGanado(CardStack[] stackPilas){
        boolean haGanado = false;
        if(stackPilas[12].isEmpty() && stackPilas[13].isEmpty() && stackPilas[14].isEmpty() && stackPilas[15].isEmpty()) {
            haGanado = false;
        }else if(stackPilas[12].peek().getValue()==13 && stackPilas[13].peek().getValue()==13 && stackPilas[14].peek().getValue()==13 && stackPilas[15].peek().getValue()==13){
            haGanado = true;
        }
        return haGanado;
    }
    public static boolean haPerdido(CardStack[] stackPilas){


        int cont = 0;
        for (int i = 0; i<8;i++){
            for (int j = 0; j<8;j++){
                if (!boolMov(stackPilas,i,j)){
                    cont++;

                    if (cont>63){
                        System.out.println("by11e bye" + i + j);
                         return true;
                    }
                }
            }
        }

        return false;
    }
    public static Queue<Card> cardStart(Queue<Card> deck){
        for (int i = 0; i<4; i++){
            for (int j = 1; j<14; j++){
                if (i==0){
                    deck.add(new Card("P",j));

                }else if (i==1){
                    deck.add(new Card("T",j));
                }else if (i==2){
                    deck.add(new Card("C",j));
                }else {
                    deck.add(new Card("D",j));
                }
            }
        }
        shuffle(deck);
        return deck;
    }


    public static Queue<Card> shuffle(Queue<Card> deck) {
        // Convertir la cola a una lista
        LinkedList<Card> lista = new LinkedList<>(deck);

        // Mezclar la lista
        Collections.shuffle(lista);

        // Limpiar la cola y aggregate los elementos mezclados
        deck.clear();
        deck.addAll(lista);

        return deck;
    }
}