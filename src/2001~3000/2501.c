#include <stdio.h>

int find_kth_divisor(int n, int k) 
{
    int count;
    int i;

    count = 0;

    for (i = 1; i <= n; i++) {
        if (n % i == 0) {
            count++;
            if (count == k) {
                return i;
            }
        }
    }

    return 0;
}

int main(void) 
{
    int n;
    int k;
    int result;

    scanf("%d %d", &n, &k);
    result = find_kth_divisor(n, k);
    printf("%d\n", result);

    return 0;
}
