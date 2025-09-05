#include <iostream>
#include <queue>
#include <vector>

int main()
{
    int nodeCount;
    std::cin >> nodeCount;

    int pairCount;
    std::cin >> pairCount;

    std::vector<std::vector<int> > adjacency;
    adjacency.resize(nodeCount + 1);

    for (int i = 0; i < pairCount; ++i)
    {
        int u;
        int v;
        std::cin >> u >> v;
        adjacency[u].push_back(v);
        adjacency[v].push_back(u);
    }

    std::vector<char> visited;
    visited.assign(nodeCount + 1, 0);

    std::queue<int> bfsQueue;
    visited[1] = 1;
    bfsQueue.push(1);

    int infectedCount = 0;

    while (!bfsQueue.empty())
    {
        int current = bfsQueue.front();
        bfsQueue.pop();

        for (int i = 0; i < static_cast<int>(adjacency[current].size()); ++i)
        {
            int next = adjacency[current][i];
            if (visited[next] == 0)
            {
                visited[next] = 1;
                bfsQueue.push(next);
                infectedCount = infectedCount + 1;
            }
        }
    }

    std::cout << infectedCount << std::endl;
    return 0;
}
