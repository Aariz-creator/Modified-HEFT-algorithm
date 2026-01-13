## HEFT Scheduling Algorithm (Java)

This repository contains a Java implementation of the HEFT (Heterogeneous Earliest Finish Time) scheduling algorithm, along with a standard/baseline version for comparison.

The HEFT algorithm is widely used in task scheduling for heterogeneous distributed systems, such as cloud and grid computing environments.

# 📌 Contents

heft.java – Implementation of the HEFT scheduling algorithm

heftstandard.java – Standard / baseline version for comparison or validation

# 🧠 About HEFT

HEFT (Heterogeneous Earliest Finish Time) is a list-based scheduling algorithm designed to minimize the overall completion time (makespan) of a task graph on heterogeneous processors.

# Key Concepts:

Directed Acyclic Graph (DAG) of tasks

Computation costs vary per processor

Communication costs between dependent tasks

Priority calculation using upward rank

Task assignment based on earliest finish time

# ⚙️ Requirements

Java JDK 8 or later

Command-line terminal or any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

# ▶️ How to Compile and Run

Clone the repository:

git clone https://github.com/your-username/heft-scheduling-java.git
cd heft-scheduling-java


Compile the Java files:

javac heft.java
javac heftstandard.java


Run the programs:

java heft
java heftstandard

📊 Output

The programs typically output:

Task scheduling order

Processor assignment

Start and finish times

Final makespan

(Exact output depends on the input task graph and implementation details.)

🧪 Use Cases

Academic study of task scheduling algorithms

Comparison between HEFT and standard scheduling approaches

Cloud computing and distributed systems research

Performance evaluation of heterogeneous systems

📈 Possible Extensions

Add support for dynamic task graphs

Visualize DAG and scheduling results

Compare with other algorithms (CPOP, PEFT, Min-Min)

Read task graphs from input files

Measure energy consumption

📝 Notes

The implementation assumes a static DAG

Communication and computation costs are predefined

Intended primarily for educational and research purposes

📄 License

This project is provided for educational use.
You may modify and extend it for learning or research purposes.
