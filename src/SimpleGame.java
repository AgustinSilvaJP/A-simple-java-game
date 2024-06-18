import java.util.Scanner;

public class SimpleGame{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num;

        do {//bucle continuidad del juego, finalizara al responder 0 en pregunta final;

            String s_word = get_word();//guardara palabra aleatoria de get_word() para el juego
            int cant_char = s_word.length();//cant de caracteres que tiene la palabra aleatoria

            char[] g_word = new char[cant_char];//se crea el array que tendra los caracteres de la palabra
            add_char_word(g_word, s_word);//agrega los caracteres del string s_word al array de chars g_word

            char[] game_board = new char[cant_char];//se crea el array que poseera los char ingresados durante el juego, en un inicio estara repleto de '_';
            add_non_char(game_board, g_word);//se agregan los '_"

            int lives = (cant_char / 2) + 1;//cantidad de oportunidades para adivinar la palabra dada

            char caracter;

            do{//bucle del juego, finaliza al adivinar la palabra o al llegar a 0 vidas

                System.out.print("\033[H\033[2J");//
                System.out.flush();               //limpia la terminal al comenzar iteracion.

                print_array_char(game_board);//imprime estado del juego.

                System.out.printf("Intentos disponibles: %d\n", lives);//imprime cant de intentos restantes.

                //solicitud de caracter.
                System.out.print("Ingrese una letra: ");
                caracter = scan.next().charAt(0);

                if(exist_char(g_word, caracter)){//verifica si el char ingresado existe en la palabra a adivinar
                    add_exist_char(g_word, game_board, caracter);//si existe, agregara el char al array de juego.
                }else{
                    lives--;//en caso de no existir resta 1 a la cant de intentos.
                }

            }while(lives > 0 && !equals_arrays(g_word, game_board));//fin bucle del juego

            System.out.print("\033[H\033[2J");//
            System.out.flush();               //limpia la terminal

            if(equals_arrays(g_word, game_board)){//verifica si adivinaste la palabra, imprimiendo mensaje y tabla de juego de ser asi
                System.out.print("gg izi, tutorial completado.\nLa palabra era: ");
                print_array_char(game_board);
            }
            if(lives == 0){//verifica si perdiste el juego, e imprime mensaje.
                System.out.println("Y esta gente me toca en ranked...");
            }

            System.out.println("Jugar denuevo?(0 = no, 1 = si): ");//mensaje de continuidad de juego
            do{
                num = scan.nextInt();
                if(num < 0 || num > 1){//verifica si el imput esta en el rango deseado [0, 1]
                    System.out.println("ERROR!\nIngrese valor valido:");
                }
            }while(num < 0 || num > 1);//bucle continuara hasta que el imput sea 0 o 1

        }while(num == 1);// fin bucle continuidad de juego

        System.out.println("ff");
    }

    //verifica si ambos arrays son iguales;
    static boolean equals_arrays(char[] array1, char[] array2){
        for(int i = 0; i < array1.length; i++){
            if(array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }
    //agrega (de existir) el caracter en el game board;
    static void add_exist_char(char[] g_word, char[] game_board, char caract){
        for(int i = 0; i < g_word.length; i++){
            if(g_word[i] == caract){//si el caracter existe en el array de la palabra se agregara el caracter en el array del game boardl;
                game_board[i] = caract;
            }
        }
    }
    //retorna true o false, si existe o no un char dado en el array de caracteres de la palabra.
    static boolean exist_char(char[] g_word, char caract){
        for(int i = 0; i < g_word.length; i++){
            if(g_word[i] == caract){
                return true;
            }
        }
        return false;
    }
    //imprime el array de caracteres dado
    static void print_array_char(char[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            System.out.print((i + 1) == array.length ? array[i] + "]\n" : array[i] + " ");
        }
    }
    //rellena el array dado con el char '_'
    static void add_non_char(char[] game_board, char[] word){
        game_board[0] = word[0];//agrega el primer caracter de la palabra dada
        for(int i = 1; i < game_board.length; i++){
            game_board[i] = '_';
        }
        if(game_board.length > 3){//si la palabra dada tiene mas de 3 caracteres, dara el char de la mitad de la palabra
            //se asigna el caracter del medio, a la tabla del juego;
            game_board[game_board.length / 2] = word[word.length / 2];
        }
    }
    //agrega los caracteres de un string dado a un array de caracteres;
    static void add_char_word(char[] char_word, String selec_word){
        for(int i = 0; i < selec_word.length(); i++){
            char_word[i] = selec_word.charAt(i);
        }
    }
    //retornara palabra que debera ser adivinada durante el juego.
    static String get_word(){
        //lista de palabras a usarse
        String[] words = {"random", "uwu", "laweafome", "hololive", "madoka", "bakemonogatari", "anime", "manga"};
        //retorno de palabra aleatoria de la lista
        return words[(int) Math.floor(Math.random() * words.length)];
    }
}