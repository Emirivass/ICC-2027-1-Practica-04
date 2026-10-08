 import java.util.Scanner;

public class CineVIPUNAM {

    // ==========================================
    // CONSTANTES
    // ==========================================

    static final int NUM_PELICULAS = 10;
    static final int ASIENTOS_POR_SALA = 12;
    static final double PRECIO_BOLETO = 100.0;
    static final double DESCUENTO_EDAD = 0.20;
    static final double DESCUENTO_CUPON = 20.0;
    static final String CUPON = "ICC20271";


    // ==========================================
    // METODO PRINCIPAL
    // ==========================================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Arreglo con las 10 peliculas
        String[] peliculas = {
            "Superman",
            "Doomsday",
            "Minecraft: Una pelicula",
            "El Conjuro",
            "Interestelar",
            "Avengers: Endgame",
            "Spider-Man: No Way Home",
            "Toy Story 5",
            "Jurassic World",
            "Avatar"
        };


        // ==========================================
        // MATRIZ DE ASIENTOS
        // ==========================================

        // true = disponible
        // false = ocupado

        boolean[][] asientos =
            new boolean[NUM_PELICULAS][ASIENTOS_POR_SALA];


        // Inicializar todos los asientos como disponibles

        for (int i = 0; i < NUM_PELICULAS; i++) {

            for (int j = 0; j < ASIENTOS_POR_SALA; j++) {

                asientos[i][j] = true;
            }
        }


        // ==========================================
        // VARIABLES DEL SISTEMA
        // ==========================================

        int boletosVendidos = 0;
        double ingresosTotales = 0.0;

        int opcion;


        // ==========================================
        // MENU PRINCIPAL
        // ==========================================

        do {

            System.out.println();
            System.out.println("==================================");
            System.out.println("         CINE UNAM VIP");
            System.out.println("==================================");
            System.out.println("1. Ver la cartelera");
            System.out.println("2. Comprar boletos");
            System.out.println("3. Imprimir el corte de caja");
            System.out.println("4. Salir");
            System.out.println("==================================");

            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();


            switch (opcion) {


                // ==================================
                // OPCION 1
                // VER CARTELERA
                // ==================================

                case 1:

                    imprimirCartelera(peliculas, asientos);

                    break;


                // ==================================
                // OPCION 2
                // COMPRAR BOLETO
                // ==================================

                case 2:

                    imprimirCartelera(peliculas, asientos);


                    // ------------------------------
                    // ELEGIR PELICULA
                    // ------------------------------

                    int peliculaElegida;
                    boolean peliculaValida;


                    do {

                        System.out.println();

                        System.out.print(
                            "Elige el numero de la pelicula (1-"
                            + NUM_PELICULAS + "): "
                        );

                        peliculaElegida = sc.nextInt();
                        sc.nextLine();


                        peliculaValida =
                            peliculaElegida >= 1
                            && peliculaElegida <= NUM_PELICULAS;


                        if (!peliculaValida) {

                            System.out.println(
                                "Numero de pelicula invalido."
                            );

                        }

                        else if (
                            contarDisponibles(
                                asientos[peliculaElegida - 1]
                            ) == 0
                        ) {

                            System.out.println(
                                "Ya no hay lugares disponibles "
                                + "para esa pelicula."
                            );

                            peliculaValida = false;
                        }


                    } while (!peliculaValida);


                    int indicePelicula =
                        peliculaElegida - 1;


                    // ------------------------------
                    // MOSTRAR SALA
                    // ------------------------------

                    imprimirSala(
                        asientos[indicePelicula]
                    );


                    // ------------------------------
                    // ELEGIR ASIENTO
                    // ------------------------------

                    int numeroAsiento;


                    do {

                        System.out.print(
                            "Elige un asiento (1-"
                            + ASIENTOS_POR_SALA + "): "
                        );

                        numeroAsiento = sc.nextInt();
                        sc.nextLine();


                        if (
                            numeroAsiento < 1
                            || numeroAsiento > ASIENTOS_POR_SALA
                        ) {

                            System.out.println(
                                "Asiento invalido. Debe estar "
                                + "entre 1 y "
                                + ASIENTOS_POR_SALA + "."
                            );
                        }


                        else if (
                            !asientos[indicePelicula]
                                [numeroAsiento - 1]
                        ) {

                            System.out.println(
                                "Ese asiento esta ocupado. "
                                + "Elige otro."
                            );
                        }


                    } while (
                        numeroAsiento < 1
                        || numeroAsiento > ASIENTOS_POR_SALA
                        || !asientos[indicePelicula]
                            [numeroAsiento - 1]
                    );


                    // ------------------------------
                    // OCUPAR ASIENTO
                    // ------------------------------

                    asientos[indicePelicula]
                        [numeroAsiento - 1] = false;


                    // ------------------------------
                    // NOMBRE DEL CLIENTE
                    // ------------------------------

                    System.out.print(
                        "Nombre del cliente: "
                    );

                    String nombre =
                        sc.nextLine().toUpperCase();


                    // ------------------------------
                    // EDAD
                    // ------------------------------

                    System.out.print("Edad: ");

                    int edad = sc.nextInt();

                    sc.nextLine();


                    // ------------------------------
                    // CUPON
                    // ------------------------------

                    System.out.print(
                        "Ingresa cupon de descuento "
                        + "(o presiona ENTER para omitirlo): "
                    );

                    String cupon = sc.nextLine();


                    // ------------------------------
                    // CALCULAR PRECIO
                    // ------------------------------

                    double total;


                    if (CUPON.equals(cupon)) {

                        total = calcularPrecio(
                            PRECIO_BOLETO,
                            edad,
                            cupon
                        );

                    }

                    else {

                        total = calcularPrecio(
                            PRECIO_BOLETO,
                            edad
                        );
                    }


                    // ------------------------------
                    // ACTUALIZAR CORTE
                    // ------------------------------

                    boletosVendidos++;

                    ingresosTotales += total;


                    // ------------------------------
                    // IMPRIMIR TICKET
                    // ------------------------------

                    System.out.println();

                    System.out.println(
                        "=================================="
                    );

                    System.out.println(
                        "TICKET DE ENTRADA CINE UNAM VIP"
                    );

                    System.out.println(
                        "Cliente: " + nombre
                    );

                    System.out.println(
                        "Pelicula: "
                        + peliculas[indicePelicula]
                    );

                    System.out.println(
                        "Asiento: " + numeroAsiento
                    );

                    System.out.println(
                        "Total pagado: $" + total
                    );

                    System.out.println(
                        "=================================="
                    );


                    break;


                // ==================================
                // OPCION 3
                // CORTE DE CAJA
                // ==================================

                case 3:

                    System.out.println();

                    System.out.println(
                        "--- CORTE DE CAJA ---"
                    );

                    System.out.println(
                        "Boletos vendidos hoy: "
                        + boletosVendidos
                    );

                    System.out.println(
                        "Ingresos totales: $"
                        + ingresosTotales
                    );

                    break;


                // ==================================
                // OPCION 4
                // SALIR
                // ==================================

                case 4:

                    System.out.println();

                    System.out.println(
                        "Gracias por usar Cine UNAM VIP."
                    );

                    break;


                // ==================================
                // OPCION INVALIDA
                // ==================================

                default:

                    System.out.println();

                    System.out.println(
                        "Opcion invalida. "
                        + "Elige una opcion del 1 al 4."
                    );

            }


        } while (opcion != 4);


        sc.close();
    }


    // ==========================================
    // FUNCION: IMPRIMIR CARTELERA
    // ==========================================

    public static void imprimirCartelera(
        String[] peliculas,
        boolean[][] asientos
    ) {

        System.out.println();
        System.out.println("========== CARTELERA ==========");


        for (int i = 0; i < peliculas.length; i++) {

            int disponibles =
                contarDisponibles(asientos[i]);


            System.out.println(
                "[" + (i + 1) + "]: "
                + peliculas[i]
                + " - Asientos disponibles "
                + disponibles
                + "."
            );
        }
    }


    // ==========================================
    // FUNCION: CONTAR ASIENTOS DISPONIBLES
    // ==========================================

    public static int contarDisponibles(
        boolean[] sala
    ) {

        int disponibles = 0;


        for (int i = 0; i < sala.length; i++) {

            if (sala[i]) {

                disponibles++;
            }
        }


        return disponibles;
    }


    // ==========================================
    // FUNCION: IMPRIMIR SALA
    // ==========================================

    public static void imprimirSala(
        boolean[] sala
    ) {

        System.out.println();

        System.out.println(
            "--- MAPA DE LA SALA ---"
        );


        for (int i = 0; i < sala.length; i++) {

            if (sala[i]) {

                System.out.print(
                    "[ " + (i + 1) + " ] "
                );
            }

            else {

                System.out.print(
                    "[ X ] "
                );
            }
        }


        System.out.println();
    }


    // ==========================================
    // SOBRECARGA 1
    // CALCULAR PRECIO POR EDAD
    // ==========================================

    public static double calcularPrecio(
        double precio,
        int edad
    ) {

        if (edad < 12 || edad > 60) {

            return precio * (1 - DESCUENTO_EDAD);
        }


        return precio;
    }


    // ==========================================
    // SOBRECARGA 2
    // CALCULAR PRECIO POR EDAD + CUPON
    // ==========================================

    public static double calcularPrecio(
        double precio,
        int edad,
        String cupon
    ) {

        double total =
            calcularPrecio(precio, edad);


        if (CUPON.equals(cupon)) {

            total -= DESCUENTO_CUPON;
        }


        // Evitar valores negativos

        if (total < 0) {

            total = 0;
        }


        return total;
    }
}