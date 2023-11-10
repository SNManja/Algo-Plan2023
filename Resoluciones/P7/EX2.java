package P7;

// NOTA: en la mayoria de casos me equivoque y tome la complejidad como si fuera una sola variable.
// Eso esta mal, claramente. Fuera del ultimo ejercicio que le di algo mas de bola.

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class EX2 {
    public class MyLinkedList<T> extends LinkedList<T> {
        public MyLinkedList<T> union(MyLinkedList<T> l1, MyLinkedList<T> l2) {
            MyLinkedList<T> res = new MyLinkedList<>();
            // O(2n**2)
            for (int i = 0; i < l1.size(); i++) { // O(n**2)
                if (!res.contains(l1.get(i))) { // O(n)
                    res.add(l1.get(i));
                }
            }
            for (int i = 0; i < l2.size(); i++) { // O(n**2)
                if (!res.contains(l2.get(i))) { // O(n)
                    res.add(l2.get(i));
                }
            }

            return res;
        }

        public MyLinkedList<T> interseccion(MyLinkedList<T> l1, MyLinkedList<T> l2) {
            MyLinkedList<T> res = new MyLinkedList<>();
            // O(n**2)
            for (int i = 0; i < l1.size(); i++) { // O(n**2)
                if (!l2.contains(l1.get(i))) { // O(n)
                    res.add(l1.get(i));
                }
            }

            return res;
        }

    }

    public class MyTreeSet<T> extends TreeSet<T> {
        public MyTreeSet<T> union(MyTreeSet<T> T1, MyTreeSet<T> T2) {
            // O(2n) = O(n)
            Iterator<T> iter1 = T1.iterator();
            MyTreeSet<T> res = new MyTreeSet<>();
            while (iter1.hasNext()) { // O(n)
                res.add(iter1.next());
            }
            Iterator<T> iter2 = T2.iterator();
            while (iter2.hasNext()) { // O(n)
                res.add(iter2.next());
            }
            return res;
        }

        public MyTreeSet<T> interseccion(MyTreeSet<T> T1, MyTreeSet<T> T2) {
            Iterator<T> iter1 = T1.iterator();

            MyTreeSet<T> res = new MyTreeSet<>();

            while (iter1.hasNext()) { // O(n^2) si no esta balanceado, si es AVL (n*log(n))
                T curr = iter1.next();
                if (T2.contains(curr)) { // O(n) si no esta balanceado, si es AVL (log(m))
                    res.add(curr);
                }
            }
            return res;
        }
    }

    public class MyHeap<T> extends PriorityQueue<T> {
        public MyHeap<T> union(MyHeap<T> H1, MyHeap<T> H2) {

            // O((log(n)*(n+log(n)) + (log(m)*(m+log(m))) = O(n*log(n) + m*log(m))
            MyHeap<T> res = new MyHeap<>();

            while (!H1.isEmpty()) { // O(1)
                T curr = H1.poll(); // O(log(n)) Intercambia el primero con el ultimo y elimina que lo hace en O(1).
                                    // Luego baja el elemento en O(log(n))

                if (!res.contains(curr)) { // O(n) // Tiene que atravezar todo el arbol
                    res.add(curr); // O(log(n)) Agrega el elemento al final y lo sube lo qe sea necesario
                }
            }
            while (!H2.isEmpty()) { // Lo mismo que arriba
                T curr = H2.poll();

                if (!res.contains(curr)) {
                    res.add(curr);
                }
            }

            return res;
        }

        public MyHeap<T> interseccion(MyHeap<T> H1, MyHeap<T> H2) {
            MyHeap<T> res = new MyHeap<>();

            while (!H1.isEmpty()) { // O(1)
                T curr = H1.poll(); // O(log(n)) Intercambia el primero con el ultimo y elimina que lo hace en O(1).
                                    // Luego baja el elemento en O(log(n))

                if (!res.contains(curr) && H2.contains(curr)) { // O(c + m) // Tiene que atravezar los arboles 2 veces
                    res.add(curr); // O(log(n)) Agrega el elemento al final y lo sube lo qe sea necesario
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {

    }

}
