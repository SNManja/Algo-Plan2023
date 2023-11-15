package P7;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
 * Dise;ar relaciones binarias entre numeros reales
 * Se construyen por extension, es decir agregando y borrando parejas de la relacion
 * 
 */

// Puedo hacer un calculo on the run. Osea, envez de almacenar los valores. Calcularlo en el momento
// En ese caso se puede achicar la implementacion y la complejidad, pero perderiamos
// el potencial de almacenamiento, que una vez calculado se devuelven los valores en O(n)
// Esta operacion tiene mucho overhead en almacenamiento, por ende, talvez no es tan ventajosa en casos de U grande
public class EX9 {
    Map<Integer, TreeSet<Integer>> bRa;
    Map<Integer, TreeSet<Integer>> aRb; // Imaginemos que esta implementado con AVL
    TreeSet<Integer> U;

    public EX9(TreeSet<Integer> conj) {

        U = conj;
        calcularRelacionesU();
    }

    private void calcularRelacionesU() {
        aRb = new HashMap<>();
        bRa = new HashMap<>();
        for (Integer a : U) { // Este for es O(U**2*log(U))
            for (Integer b : U) { // O(|U| * log(U))
                if (condicion(a, b)) { // O(1)
                    aRb.get(a).add(b); // O(log(U)) /// Habria q escribirlo como deberia ser en java pero paja
                }
                if (condicion(b, a)) {
                    bRa.get(a).add(b);
                }
            }
        }
    }

    private boolean condicion(int a, int b) {
        return a == b; // colocar condicion cualquiera
    }

    public TreeSet<Integer> relacionaRb(Integer a) {
        return aRb.get(a);
    }

    public TreeSet<Integer> relacionbRa(Integer a) {
        return bRa.get(a);
    }
}
