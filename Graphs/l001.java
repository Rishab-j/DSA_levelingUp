import java.util.ArrayList;

/**
 * l001
 */
public class l001 {

    static class Edge {
        int v = 0;
        int w = 0;

        Edge(int b, int w) {
            this.v = b;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + "->");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(0, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 0, 10);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        display();
    }

    public static int findEdge(int v1, int v2) {
        int vtx = -1;
        for (int i = 0; i < graph[v1].size(); i++) {
            Edge e = graph[v1].get(i);
            if (e.v == v2) {
                vtx = i;
                break;
            }
        }
        return vtx;
    }

    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int vtx) {
        while (graph[vtx].size() != 0) {
            Edge e = graph[vtx].get(graph[vtx].size() - 1);
            removeEdge(vtx, e.v);
        }
    }

    public static boolean hasPath(int s, int d, boolean[] visited) {
        if (s == d) {
            return true;
        }
        visited[s] = true;
        boolean res = false;
        for (int i = 0; i < graph[s].size(); i++) {
            Edge ce = graph[s].get(i);
            if (!visited[ce.v]) {
                res = res || hasPath(ce.v, d, visited);
            }
        }
        return res;
    }

    public static int allPaths(int s, int d, boolean[] visited, ArrayList<Edge>[] graph, String path) {

        if (s == d) {
            System.out.println(path + " " + d);
            return 1;
        }

        int count = 0;
        visited[s] = true;
        for (int i = 0; i < graph[s].size(); i++) {
            Edge ce = graph[s].get(i);
            if (!visited[ce.v]) {
                count += allPaths(ce.v, d, visited, graph, path + " " + s);
            }
        }
        visited[s] = false;
        return count;
    }

    

    public static void solve() {
        constructGraph();
        // removeVtx(3);
        int ans = allPaths(0, 6, new boolean[N], graph, "");
        System.out.println(ans);
    }


    public static void main(String[] args) {
        solve();
    }
}