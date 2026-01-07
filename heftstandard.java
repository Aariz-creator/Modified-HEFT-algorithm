import java.util.*;

class Processor {
    int id, load = 0;
    public Processor(int id) { this.id = id; }
}

class Task {
    int id, computationCost;
    double rank;
    Processor assignedProcessor;
    List<Task> successors = new ArrayList<>();
    List<Task> predecessors = new ArrayList<>();
    int startTime = 0, finishTime = 0;
    public Task(int id, int cost) {
        this.id = id; this.computationCost = cost;
    }
}

class Edge {
    Task source, target;
    int communicationCost;
    public Edge(Task s, Task t, int c) {
        source = s; target = t; communicationCost = c;
    }
}

public class StandardHeft {
    static double computeRank(Task task, Map<Integer, Map<Integer, Edge>> graph, Set<Integer> visited) {
        if (visited.contains(task.id)) return task.rank;
        visited.add(task.id);
        double maxSuccRank = 0;
        for (Task succ : task.successors) {
            Edge e = graph.get(task.id).get(succ.id);
            double succRank = computeRank(succ, graph, visited);
            maxSuccRank = Math.max(maxSuccRank, e.communicationCost + succRank);
        }
        return task.rank = task.computationCost + maxSuccRank;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of tasks: ");
        int nTasks = sc.nextInt();
        Task[] tasks = new Task[nTasks];
        for (int i = 0; i < nTasks; i++) {
            System.out.print("Enter computation cost of task " + i + ": ");
            tasks[i] = new Task(i, sc.nextInt());
        }

        System.out.print("Enter number of edges (dependencies): ");
        int nEdges = sc.nextInt();
        Map<Integer, Map<Integer, Edge>> graph = new HashMap<>();
        for (int i = 0; i < nEdges; i++) {
            System.out.print("Enter edge (from to cost): ");
            int u = sc.nextInt(), v = sc.nextInt(), cost = sc.nextInt();
            tasks[u].successors.add(tasks[v]);
            tasks[v].predecessors.add(tasks[u]);
            graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, new Edge(tasks[u], tasks[v], cost));
        }

        System.out.print("Enter number of processors: ");
        int m = sc.nextInt();
        Processor[] processors = new Processor[m];
        for (int i = 0; i < m; i++) processors[i] = new Processor(i);

        for (Task t : tasks)
            computeRank(t, graph, new HashSet<>());
        Arrays.sort(tasks, (a, b) -> Double.compare(b.rank, a.rank));

        for (Task t : tasks) {
            int bestTime = Integer.MAX_VALUE;
            Processor bestProcessor = null;
            for (Processor p : processors) {
                int readyTime = 0;
                for (Task pred : t.predecessors) {
                    int comm = graph.get(pred.id).get(t.id).communicationCost;
                    readyTime = Math.max(readyTime, pred.finishTime + (pred.assignedProcessor == p ? 0 : comm));
                }
                int start = Math.max(p.load, readyTime);
                int finish = start + t.computationCost;
                if (finish < bestTime) {
                    bestTime = finish;
                    bestProcessor = p;
                }
            }
            t.startTime = Math.max(bestProcessor.load, t.startTime);
            t.finishTime = t.startTime + t.computationCost;
            bestProcessor.load = t.finishTime;
            t.assignedProcessor = bestProcessor;
        }

        int makespan = Arrays.stream(tasks).mapToInt(t -> t.finishTime).max().getAsInt();
        System.out.println("\n--- Standard HEFT Schedule ---");
        for (Task t : tasks) {
            System.out.println("Task " + t.id + " → P" + t.assignedProcessor.id +
                    " | Start: " + t.startTime + ", Finish: " + t.finishTime);
        }
        System.out.println("Standard HEFT Makespan: " + makespan);
    }
}