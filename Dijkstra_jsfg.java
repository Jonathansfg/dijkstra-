import java.util.Scanner;

public class Dijkstra_jsfg {
    private static final int INFINITO = 999;

    public int dis[];
    public int cost[][];

    public Dijkstra_jsfg(int n) {
        dis = new int[n + 1];
        cost = new int[n + 1][n + 1];
    }

    public void inicializarMatrizcosts(Scanner in, int nodo) {
        System.out.println("Ingrese los pesos de la matriz: ");
        for (int i = 1; i <= nodo; i++) {
            for (int j = 1; j <= nodo; j++) {
                cost[i][j] = in.nextInt();
                if (cost[i][j] == 0 && i != j) {
                    cost[i][j] = INFINITO;
                }
            }
        }
    }

    public void calcularDistancias(int n, int s) {
        int flag[] = new int[n + 1];
        int min = 0;

        for (int i = 1; i <= n; i++) {
            flag[i] = 0;
            dis[i] = cost[s][i];
        }

        flag[s] = 1;
        for (int f = 2; f <= n; f++) {
            int minimo = INFINITO;
            for (int j = 1; j <= n; j++) {
                if (dis[j] < minimo && flag[j] != 1) {
                    minimo = dis[j];
                    min = j;
                }
            }
            flag[min] = 1;
            for (int j = 1; j <= n; j++) {
                if (dis[min] + cost[min][j] < dis[j] && flag[j] != 1) {
                    dis[j] = dis[min] + cost[min][j];
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Número de nodos: ");
        int nodo = in.nextInt();
        Dijkstra_jsfg d = new Dijkstra_jsfg(nodo);
        d.inicializarMatrizcosts(in, nodo);
        
        System.out.println("Origen: ");
        int fuente = in.nextInt();

        d.calcularDistancias(nodo, fuente);

        System.out.println("El camino más corto es " + fuente + " y los demás vértices:");
        for (int i = 1; i <= nodo; i++) {
            if (i != fuente) {
                System.out.println("Fuente: " + fuente + "\tDestino: " + i + "\tcost mínimo: " + d.dis[i]);
            }
        }
        in.close();
    }
}
