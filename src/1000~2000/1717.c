#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 1000000

int parent[MAX_SIZE + 1];
int rank[MAX_SIZE + 1];

void initialize(int n) 
{
    size_t i;
    for (i = 0; i <= n; i++) {
        parent[i] = i;
        rank[i] = 0;
    }
}

int find(int x) 
{
    if (parent[x] != x) {
        parent[x] = find(parent[x]);
    }
    return parent[x];
}

void union_sets(int a, int b) 
{
    int root_a;
    int root_b;

    root_a = find(a);
    root_b = find(b);

    if (root_a != root_b) {
        if (rank[root_a] < rank[root_b]) {
            parent[root_a] = root_b;
        } else if (rank[root_a] > rank[root_b]) {
            parent[root_b] = root_a;
        } else {
            parent[root_b] = root_a;
            rank[root_a]++;
        }
    }
}

int main(void) 
{
    int n;
    int m;
    size_t i;

    scanf("%d %d", &n, &m);

    initialize(n);

    for (i = 0; i < m; i++) {
        int command;
        int a;
        int b;

        scanf("%d %d %d", &command, &a, &b);

        if (command == 0) {
            union_sets(a, b);
        } else if (command == 1) {
            if (find(a) == find(b)) {
                printf("YES\n");
            } else {
                printf("NO\n");
            }
        }
    }

    return 0;
}
