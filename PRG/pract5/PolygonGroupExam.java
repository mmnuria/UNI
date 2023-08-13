package pract5;

/**
 * Clase PolygonGroupExam. Grupo de poligonos en el plano.
 * Los poligonos estan en orden segun la secuencia en que se anyaden
 * al grupo, de manera que se considera que cada poligono esta mas 
 * arriba en el grupo que los poligonos anteriores, o dicho de otro 
 * modo, se superpone a los anteriores. 
 * Se supone que el orden del grupo da la secuencia en que se dibujan
 * los poligonos, de manera que cada uno se dibuja por encima de los
 * anteriores, superponiendose a aquellos con los que solape.
 *  
 * La manera de seleccionar un poligono es dando un punto visible 
 * del poligono, es decir, dando un punto que no pertenezca
 * a los poligonos que aparecen superpuestos en el dibujo.
 *
 * @author PRG
 * @version Curso 2022-23
 */
public class PolygonGroupExam {
    private NodePol front, back;
    private int size;
    
    /** Crea un grupo de 0 poligonos. */
    public PolygonGroupExam() {
        front = null;
        back = null;
        size = 0;
    }
    
    /** Anyade al grupo, arriba del todo, un poligono dado. 
     *  @param pol Polygon, el poligono.
     */
    public void add(Polygon pol) {  
        front = new NodePol(pol, front);
        if (back == null) { back = front; }
        size++;
    }
    
    /** Devuelve el numero de poligonos del grupo,  
     *  esto es, la talla del grupo.
     *  return int, la talla.
     */
    public int getSize() { return size; }
    
    /** Devuelve la referencia al nodo con el poligono  
     *  del frente del grupo.
     *  return NodePol, nodo con el poligono del frente.
     */
    public NodePol getFront() { return front; }
    
    /** Devuelve la referencia al nodo con el poligono 
     *  del fondo del grupo.
     *  return NodePol, nodo con el poligono del fondo.
     */
    public NodePol getBack() { return back; }
    
    /** Devuelve un array con la secuencia de poligonos del grupo, 
     *  por orden desde el de mas abajo al de mas arriba.
     *  @return Polygon[], el array.
     */
    public Polygon[] toArray() {
        Polygon[] result = new Polygon[size];
        NodePol aux = front;
        for (int i = size - 1; i >= 0; i--) {           
            result[i] = aux.data;
            aux = aux.next;
        }
        return result;
    } 
    
    /** Busca en el grupo descendentemente, de mas arriba
     *  a mas abajo, el primer poligono que contiene a un 
     *  punto dado. Devuelve un array de NodePol tal que:
     *  - la componente 1 es el nodo con el poligono que contiene 
     *    a p (null si no hubiera ninguno)
     *  - la componente 0 es el nodo anterior (null si no está definido).
     *  @param p Point, el punto.
     *  @return NodePol[], el array resultado.
     */
    private NodePol[] search(Point p) {
        NodePol aux = front, prevAux = null;
        while (aux != null && !aux.data.inside(p)) {
            prevAux = aux;
            aux = aux.next;
        }
        NodePol[] s = new NodePol[2];
        s[0] = prevAux; s[1] = aux;
        return s;
    }
    
    /** Anyade al grupo un poligono pol dado, situandolo debajo del poligono 
     *  seleccionado mediante el punto p. El metodo no hace nada si no 
     *  hay ningun poligono que contenga a p.
     *  @param p Point, el punto.
     *  @param pol Polygon, el poligono.
     */
    public void addUnder(Point p, Polygon pol) {
        // COMPLETAR
        NodePol[] searchResult = search(p);
        NodePol prevNode = searchResult[0];
        NodePol containingNode = searchResult[1];
    
        if (containingNode != null) {
            NodePol newNode = new NodePol(pol, containingNode);
            if (prevNode != null) {
                prevNode.next = newNode;
            } else {
                front = newNode;
            }
            size++;
        }
        
    }
}
