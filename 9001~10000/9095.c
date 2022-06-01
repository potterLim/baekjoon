#include <stdio.h>
 
int main() {
    int arr[11];
    arr[0] = 0;
    arr[1] = 1;
    arr[2] = 2;
    arr[3] = 4;
    int count;
    int n;
    int i;

    for (i = 4; i < 11; i++) {
        arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
    }
 
    scanf("%d", &count);
    for (i = 0; i < count; ++i) {
        scanf("%d", &n);
        printf("%d\n", arr[n]);
    }
    return 0;
}
