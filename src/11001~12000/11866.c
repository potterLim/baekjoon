#include <stdio.h>

#define MAX_N 1000

int main(void)
{
    int n;
    int k;
    int people[MAX_N];
    int count;
    int idx;
    int i;
    int removed;

    scanf("%d %d", &n, &k);

    for (i = 0; i < n; ++i) {
        people[i] = i + 1;
    }

    printf("<");
    count = n;
    idx = 0;

    while (count > 0) {
        int step;
        step = (k - 1) % count;

        idx = (idx + step) % count;

        removed = people[idx];

        printf("%d", removed);
        if (count > 1) {
            printf(", ");
        }

        for (i = idx; i < count - 1; ++i) {
            people[i] = people[i + 1];
        }

        --count;
        if (idx == count) {
            idx = 0;
        }
    }

    printf(">\n");
    return 0;
}
