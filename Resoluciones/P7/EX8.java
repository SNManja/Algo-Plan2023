package P7;

import java.util.Collections;
import java.util.LinkedList;

/*
 * ESTA MAL TENDRIA QUE HABER SIDO CON DICCIONARIOS 
 */

public class EX8 {
    public class Set {
        LinkedList<Integer> data; // Esto es el equivalente a un ABB. Asumo que es AVL para mejorar tiempos

        public Set() {
            data = new LinkedList<>();
        }

        public Set(Integer[] arr) {
            for (int i = 0; i < arr.length; i++) {
                data.add(arr[i]);
            }
        }

        public void add(int val) { // O(2n) masomenos, osea O(n)

            if (val < data.getFirst()) { // O(1) si es primero
                data.addFirst(val); // O(1)
            } else if (val > data.getLast()) { // O(1) si es ultimo
                data.addLast(val);
            } else {
                if (data.contains(val)) {
                    return; // Si ya lo contiene no me interesa, es un conjunto O(n)
                }
                data.add(val);
                Collections.sort(data);
                // Asumiendo que todo esta agregado y el sort lo hace moviendo el ultimo
                // agregado a la posicion correspondiente, tardando O(n)
            }

        }

        public void delete(int val) {
            data.remove(val);
        }

        public int getMin() {
            return data.getFirst();
        }

        public int getMax() {
            return data.getLast();
        }

        public void union(LinkedList<Integer> l) {
            for (int elem : l) {
                data.add(elem);

            }
        }

    }
}
