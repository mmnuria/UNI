package pract5;

import graph2D.Graph2D;
import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Clase programa para facilitar la prueba del codigo, desarrollado por 
 * el alumno, para el metodo addUnder.
 * El main crea una secuencia con 3 cuadrados de igual tamanyo superpuestos:
 * verde en el origen, rojo desplazado a la derecha y arriba y 
 * azul desplazado tambien a la derecha y arriba.
 * Se realizan cuatro pruebas partiendo siempre de la secuencia original
 * y anyadiendo mediante el metodo addUnder(Point, Polygon) un Polygon
 * que se crea como un rectangulo de color naranja rN:
 * 1.- Crear un punto fuera de cualquier figura (pF) y llamar al metodo
 *     addUnder(pF, rN) para ver que la figura no se modifica.
 * 2.- Crear un punto sobre el cuadrado verde (pCV) y llamar al metodo
 *     addUnder(pCV, rN) para ver que el rectangulo naranja queda por 
 *     debajo del verde (y tambien por debajo del rojo y del azul).
 * 3.- Crear un punto sobre el cuadrado rojo (pCR) y llamar al metodo
 *     addUnder(pCR, rN) para ver que el rectangulo naranja queda por 
 *     encima del verde y por debajo del rojo (y tambien del azul).
 * 4.- Crear un punto sobre el cuadrado azul (pCA) y llamar al metodo
 *     addUnder(pCA, rN) para ver que el rectangulo naranja queda por 
 *     encima del verde y del rojo y por debajo del azul.
 *     
 * Esta clase es para uso particular del alumno. 
 * No se entrega.
 *
 * @author PRG
 * @version Curso 2022-23
 */
public class TestAddUnder {
    private TestAddUnder() { } // No se usan objetos de esta clase
    
    public static void main(String[] args)  {
        Scanner keyB = new Scanner(System.in);
        // Crea un grupo de 3 cuadrados que se superponen formando escalera                    
        Graph2D gd = new Graph2D(-20, 20, -20, 20, 600, 400, Color.WHITE, "Prueba addUnder");
        PolygonGroupExam g = makeGroup();    
        Polygon[] arrayPol = g.toArray();
        drawGroup(gd, g);
        System.out.println("Veras 3 cuadrados superpuestos formando escalera.");
        System.out.println("Secuencia: Verde -> Rojo -> Azul");
        System.out.println("Pulsa INTRO para continuar..."); 
        String s = keyB.nextLine();
        
        // Crea un rectangulo naranja   
        double[] x = {4.0, 10.0, 10.0, 4.0};
        double[] y = {10.0, 10.0, -10.0, -10.0};
        Polygon rN = new Polygon(x, y);
        rN.setColor(Color.ORANGE);
      
        // Anyade el rN en un punto fuera de los cuadrados
        System.out.println("** Prueba 1: llamar addUnder para anyadir un rectangulo naranja... ");
        System.out.println("   por debajo de la figura marcada por el punto "
            + "(ninguna en este caso).");
        // Crea un punto fuera de cualquier cuadrado
        Point pF = new Point(-10.0, 10.0);  
        gd.drawPoint(pF.getX(), pF.getY(), Color.BLACK, 8);
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.addUnder(pF, rN);
        drawGroup(gd, g); 
        System.out.println("... Comprueba que la figura queda igual, sin cambios.");  
        boolean okFront = g.getFront() != null && equals(g.getFront().data, arrayPol[2]);
        boolean okBack = g.getBack() != null && equals(g.getBack().data, arrayPol[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        // Anyade el rN bajo el verde (pCV)
        g = makeGroup();  
        drawGroup(gd, g);
        System.out.println("Volvemos a la figura original.");
        System.out.println("** Prueba 2: llamar addUnder para anyadir un rectangulo naranja... ");
        System.out.println("   por debajo de la figura marcada por el punto "
            + "(el cuadrado verde).");       
        // Crea un punto sobre el cuadrado verde
        Point pCV = new Point(-3.0, -4.0); 
        gd.drawPoint(pCV.getX(), pCV.getY(), Color.BLACK, 8);
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        g.addUnder(pCV, rN);
        drawGroup(gd, g);   
        System.out.println("... Comprueba que debe estar por debajo del verde, "
            + "y tambien del rojo y del azul.");   
        okFront = g.getFront() != null && equals(g.getFront().data, arrayPol[2]);
        okBack = g.getBack() != null && equals(g.getBack().data, rN);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }    
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        // Anyade el rN bajo el rojo (pCR)
        g = makeGroup();  
        drawGroup(gd, g);
        System.out.println("Volvemos a la figura original.");
        System.out.println("** Prueba 3: llamar addUnder para anyadir un rectangulo naranja... ");
        System.out.println("   por debajo de la figura marcada por el punto (el cuadrado rojo).");
        // Crea un punto sobre el cuadrado rojo
        Point pCR = new Point(1.0, 10.0); 
        gd.drawPoint(pCR.getX(), pCR.getY(), Color.BLACK, 8);
        g.addUnder(pCR, rN);
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        drawGroup(gd, g);  
        System.out.println("... Comprueba que debe estar por debajo del rojo (y del azul) "
            + "pero por encima del verde.");  
        okFront = g.getFront() != null && equals(g.getFront().data, arrayPol[2]);
        okBack = g.getBack() != null && equals(g.getBack().data, arrayPol[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        
        // Anyade el rN bajo el azul (pCA)
        g = makeGroup();  
        drawGroup(gd, g);
        System.out.println("Volvemos a la figura original.");
        System.out.println("** Prueba 4: llamar addUnder para anyadir un rectangulo naranja... ");
        System.out.println("   por debajo de la figura marcada por el punto (el cuadrado azul).");
        // Crea un punto sobre el cuadrado azul
        Point pCA = new Point(16.0, 16.0); 
        gd.drawPoint(pCA.getX(), pCA.getY(), Color.BLACK, 8);
        g.addUnder(pCA, rN);
        System.out.println("Pulsa INTRO para continuar..."); 
        s = keyB.nextLine();
        drawGroup(gd, g);    
        System.out.println("... Comprueba que debe estar encima del verde y del rojo "
            + "pero por debajo del azul."); 
        okFront = g.getFront() != null && equals(g.getFront().data, arrayPol[2]);
        okBack = g.getBack() != null && equals(g.getBack().data, arrayPol[0]);
        if (!okFront || !okBack) {
            System.out.println("    --> ERROR: Problemas con las referencias front y/o back");
        }
        System.out.println("Fin del proceso!!!");
    }
    
    /** 
     * Muestra en la salida grafica un grupo de poligonos dado.
     * @param gd Graph2D, el grafo.
     * @param p PolygonGroup, el grupo de poligonos.
     */
    private static void drawGroup(Graph2D gd, PolygonGroupExam g) {
        gd.clear(); 
        Polygon[] aPol = g.toArray();
        for (int i = 0; i < aPol.length; i++) {
            gd.fillPolygon(aPol[i].verticesX(), aPol[i].verticesY(),
                aPol[i].getColor(), 2);   
        }   
    }
    
    /** 
     * Crea un grupo con 3 cuadrados en escalera.
     * @return p PolygonGroup, el grupo de poligonos.
     */
    private static PolygonGroupExam makeGroup() {
        PolygonGroupExam g = new PolygonGroupExam();
        // el cuadrado verde centrado en el origen de tamanyo 6
        double[] x1 = {-6.0, -6.0, 6.0, 6.0};
        double[] y1 = {-6.0, 6.0, 6.0, -6.0};
        Polygon pol = new Polygon(x1, y1);
        pol.setColor(Color.GREEN);
        g.add(pol);
        // el cuadrado rojo del mismo tamanyo y centrado en (6.0, 6.0)
        double[] x2 = {0.0, 0.0, 12.0, 12.0};
        double[] y2 = {0.0, 12.0, 12.0, 0.0};
        pol = new Polygon(x2, y2);
        pol.setColor(Color.RED);
        g.add(pol);
        // el cuadrado azul del mismo tamanyo y centrado en (12.0, 12.0)
        double[] x3 = {6.0, 6.0, 18.0, 18.0};
        double[] y3 = {6.0, 18.0, 18.0, 6.0};
        pol = new Polygon(x3, y3);
        pol.setColor(Color.BLUE);
        g.add(pol);
        
        return g;
    }
    
    /** Comprueba si dos poligonos son iguales.
     *  @param p Polygon.
     *  @param q Polygoon.
     *  @return boolean, true si son iguales; en caso contrario, false. 
     */
    private static boolean equals(Polygon p, Polygon q) {
        return p != null && q != null 
            && p.getColor().equals(q.getColor())
            && Arrays.equals(p.verticesX(), q.verticesX())
            && Arrays.equals(p.verticesY(), q.verticesY());
    }
}
