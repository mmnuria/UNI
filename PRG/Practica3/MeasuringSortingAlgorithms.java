 

import java.util.Locale;
/** Clase MeasuringSortingAlgorithms: Estudio empirico de costes de 
 *  los metodos de ordenacion.
 *  @author PRG - ETSInf
 *  @version Curso 2022-2023
 */
public class MeasuringSortingAlgorithms {
    // Constantes que definen los parametros de medida
    public static final int MAXTALLA = 10000, INITALLA = 1000; 
    public static final int INCRTALLA = 1000;
    public static final int REPETICIONESQ = 200, REPETICIONESL = 20000;
    public static final double NMS = 1e3;  // relacion micro - nanosegons
    
    /** No hay objetos de esta clase. */
    private MeasuringSortingAlgorithms() { }
    
    /** Crea un array de int de talla t con valores a 0.
     *  @param t int, la talla.
     *  @result int[], el array generado.
     */
    private static int[] createArray(int t) { 
        int[] a = new int[t];
        return a;
    }

    /** Rellena los elementos de un array a de int 
     *  con valores aleatorios entre 0 y a.length-1.
     *  @param a int[], el array.
     */
    private static void fillArrayRandom(int[] a) {
        for(int i = 0; i< a.length; i++ ){
            a[i] = (int) (Math.random() * a.length);
        }
    }
  
    /** Rellena los elementos de un array a de forma creciente,
     *  con valores desde 0 hasta a.length-1.
     *  @param a int[], el array.
     */
    private static void fillArraySortedInAscendingOrder(int[] a) { 
        for(int i = 0; i< a.length; i++ ){
            a[i] = i;
        }
    }

    /** Rellena los elementos de un array a de forma decreciente,
     *  con valores desde a.length-1 hasta 0.
     *  @param a int[], el array.
     */
    private static void fillArraySortedInDescendingOrder(int[] a) { 
        for(int i = 0; i< a.length; i++ ){
            a[i] = a.length - 1 + i;
        }
    }
  
    public static void measuringSelectionSort() {
        long ti = 0, tf = 0, tt = 0; // Tiempos inicial, final y total 
        // Imprimir cabecera
        System.out.println("# Seleccion. Tiempos en microsegundos");
        System.out.print("# Talla    Promedio \n");
        System.out.print("#------------------\n");
               
        // COMPLETAR
        /*// Este bucle repite el proceso para varias tallas
        for( int size = MIN_SIZE; size <= MAX_SIZE; size += STEP_OF_SIZE ) {

            // Create the array
            int[] a = createRandomArray( size );
      
            for (int r = 0; r < REPETITIONS; r++) {
                ti = System.nanoTime();      // Initial timestamp
                MeasurableAlgorithms.selectionSort( a );
                tf = System.nanoTime();      // Final timestamp
                tt += (tf - ti);             // Lapse for this run is accumulated
            }
            
            // Average running time for the average case
            double averageTime = (double) tt / REPETITIONS;
            
            // Print results
            System.out.printf( Locale.US, "%10d %12.3f\n", 
                              size, averageTime / NMS );
        }
        */
    }

    public static void measuringInsertionSort() {
        long ti = 0, tf = 0, tt = 0; // Temps inicial, final i total        
        // Imprimir cabecera de resultados
        System.out.println("# Insercion. Tiempos en microsegundos.");
        System.out.print("# Talla    Mejor       Peor     Promedio \n");
        System.out.print("#----------------------------------------\n");

        // COMPLETAR
        /* // Este bucle repite el proceso para varias tallas
        for( int size = MIN_SIZE; size <= MAX_SIZE; size += STEP_OF_SIZE ) {

            // Create the array (worst)
            int[] a = createArraySortedInDescendingOrder( size );
      
            // Worst case
            tt = 0;  // Accumulated time at the beginning is zero
            for( int r = 0; r < REPETITIONS; r++ ) {
                ti = System.nanoTime();      // Initial timestamp
                MeasurableAlgorithms.insertionSort( a );
                tf = System.nanoTime();      // Final timestamp
                tt += (tf - ti);             // Lapse for this run is accumulated
            }
            // Average running time for the worst case
            double worstTime = (double) tt / REPETITIONS;
            
            // Create the array (best)
            a = createArraySortedInAscendingOrder( size );
            
            // Best case
            tt = 0;  // Accumulated time at the beginning is zero
            for (int r = 0; r < REPETITIONS; r++) {
                ti = System.nanoTime();      // Initial timestamp
                MeasurableAlgorithms.insertionSort( a );
                tf = System.nanoTime();      // Final timestamp
                tt += (tf - ti);             // Lapse for this run is accumulated
            } 
            // Average running time for the best base
            double bestTime = (double) tt / REPETITIONS; 
            
            // Create the array (average)
            a = createRandomArray( size );
            
            // Average case
            tt = 0;  // Accumulated time at the beginning is zero
            for (int r = 0; r < REPETITIONS; r++) {
                int value = generator.nextInt( size ); // Value to be looked for
                ti = System.nanoTime();      // Initial timestamp
                MeasurableAlgorithms.insertionSort( a );
                tf = System.nanoTime();      // Final timestamp
                tt += (tf - ti);             // Lapse for this run is accumulated
            }
            // Average running time for the average case
            double averageTime = (double) tt / REPETITIONS;

            // Print results
            System.out.printf( Locale.US, "%10d %12.3f %12.3f %12.3f\n", 
                              size, worstTime / NMS, bestTime / NMS, averageTime / NMS );
        }
        */
    }
  

    private static void help() {
        String msg = "Uso: java MeasurigSortingAlgorithms num_algoritmo";
        System.out.println(msg);
        System.out.println("   donde num_algoritmo es: ");
        System.out.println("   1 -> Seleccion");
        System.out.println("   2 -> Insercion");
    }

    /*
     * public static void measuringMergeSort()
    { 
        // To be completed by students
        
        long ti = 0, // Initial timestamp
             tf = 0, // Final timestamp
             tt = 0; // Total timestamp
       
        // Print result header
        System.out.printf( "# MergeSort. Time in microseconds\n" );
        System.out.printf( "#     Size      Average\n" );
        System.out.printf( "#----------------------\n" );

        // Este bucle repite el proceso para varias tallas
        for( int size = 1; size <= Math.pow(2, 19); size = size * 2 ) {

            // Create the array
            int[] a = createRandomArray( size );
      
            for (int r = 0; r < REPETITIONS; r++) {
                ti = System.nanoTime();      // Initial timestamp
                MeasurableAlgorithms.mergeSort( a, 0, a.length - 1 );
                tf = System.nanoTime();      // Final timestamp
                tt += (tf - ti);             // Lapse for this run is accumulated
            }
            
            // Average running time for the average case
            double averageTime = (double) tt / REPETITIONS;
            
            // Print results
            System.out.printf( Locale.US, "%10d %12.3f\n", 
                              size, averageTime / NMS );
        }
    }

     */
    
    public static void main(String[] args) {
        if (args.length != 1) { help(); } 
        else {
            try {
                int a = Integer.parseInt(args[0]);
                switch (a) {
                    case 1: 
                        measuringSelectionSort(); 
                        break;
                    case 2: 
                        measuringInsertionSort(); 
                        break;
                    default: 
                        help();
                }
            } catch (Exception e) {
                help(); 
            }
        }
    }
}
