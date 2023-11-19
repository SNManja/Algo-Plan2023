package P7;

public class EX7 {
    private class maxHeapAcotada {
        Nodo[] heap;
        int cota;
        int puntero;

        class Nodo {

            int partido;
            int valor;
            int div;
            int index;

            public Nodo(int n, int p) {
                partido = p;
                valor = n;
                div = 1;
                index = -1; // CHECKEAR CUANDO ESTE EL FLOYD
            }
        }

        /*
         * 
         * pred InvRep(arr: Arr<Nodo>){
         * forAll i in Z : 0<= i < i * 2 + 1 < arr.size() => (arr[i] >= arr[i * 2 + 1])
         * &&
         * forAll i in Z : 0<= i < i * 2 + 2 < arr.size() => (arr[i] >= arr[i * 2 + 2])
         * }
         * 
         */
        public boolean invariante() { // Esta bien que el invariante este aca? o tiene q ser especificado
            return cota <= heap.length && puntero <= heap.length && cota >= 0 && puntero >= 0 && bienOrdenado(heap);
        }

        public boolean bienOrdenado(Nodo[] heap) {
            for (int i = 0; i < heap.length; i++) {
                if ((2 * i + 1) < heap.length && heap[2 * i + 1].valor > heap[i].valor) {
                    return false;
                }
                if ((2 * i + 2) < heap.length && heap[2 * i + 2].valor > heap[i].valor) {
                    return false;
                }
            }
            return true;
        }

        public maxHeapAcotada(int[] arr) {
            heap = new Nodo[arr.length];
            for (int i = 0; i < arr.length; i++) {
                Nodo curr = new Nodo(arr[i], i);
                curr.partido = i;
                heap[i] = curr;
            }

            array2Heap(heap);
        }

        public void array2Heap(Nodo[] arreglo) {
            this.heap = arreglo;
            this.puntero = arreglo.length;
            this.cota = arreglo.length;
            for (int i = this.puntero - 1; i > 0; i--) {
                bajarElemento(heap[i]);
            }
        }

        public Nodo leftNode(Nodo n) {
            return heap[n.index * 2 + 1];
        }

        public Nodo rightNode(Nodo n) {
            return heap[n.index * 2 + 2];
        }

        public Nodo fatherNode(Nodo n) {
            int father = (n.index - 1) / 2; // CHECKEAR
            return heap[father];
        }

        public boolean hasLeft(Nodo n) {
            return (n.index * 2) + 1 < heap.length;
        }

        public boolean hasRight(Nodo n) {
            return (n.index * 2) + 2 > heap.length;
        }

        public boolean hasFather(Nodo n) {
            return (n.index * 2) + 2 < heap.length;
        }

        public void desencolar() {
            heap[0].valor = heap[heap.length - 1].valor;
            puntero--;
            heap[heap.length - 1] = null; // Lo que tomemos como nulo
            bajarElemento(heap[0]);
        }

        private void bajarElemento(Nodo n) {
            if (hasRight(n)) { // Como el arbol es izquierdista sabemos que si tiene right tiene left
                if (n.valor < rightNode(n).valor && (leftNode(n).valor < rightNode(n).valor)) {
                    swap(n, rightNode(n));
                    bajarElemento(rightNode(n)); // continua recursion por der
                }
            } else if (hasLeft(n)) { // En caso que solo tenga left
                if (n.valor < leftNode(n).valor) {
                    swap(n, leftNode(n));
                    bajarElemento(leftNode(n)); // Continua recursion por izq
                }
            }
        }

        private void swap(Nodo x, Nodo y) {
            int temp = x.valor;
            x.valor = y.valor;
            y.valor = temp;
        }

        public void encolar(Nodo n) {
            if (puntero > cota - 1) {
                // agregar error handling
            }
            n.index = puntero + 1;
            heap[puntero + 1] = n;
            subirElemento(n);
            puntero++;
        }

        private void subirElemento(Nodo n) {
            if (hasFather(n)) {
                if (fatherNode(n).valor < n.valor) {
                    swap(n, fatherNode(n));
                    subirElemento(fatherNode(n));
                }
            }
        }
    }
}
