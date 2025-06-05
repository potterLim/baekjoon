#include <stdio.h>
#include <stdlib.h>

#define MAX 500000

int a[MAX];
int tmp[MAX];
int count = 0;
int result = -1;
int k;

void merge(int arr[], int left, int mid, int right)
{
    int i = left;
    int j = mid + 1;
    int t = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            tmp[t++] = arr[i++];
        } else {
            tmp[t++] = arr[j++];
        }
    }

    while (i <= mid) {
        tmp[t++] = arr[i++];
    }

    while (j <= right) {
        tmp[t++] = arr[j++];
    }

    i = left;
    t = 0;

    while (i <= right) {
        arr[i++] = tmp[t++];
        count++;
        if (count == k) {
            result = arr[i - 1];
        }
    }
}

void merge_sort(int arr[], int left, int right)
{
    int mid;

    if (left < right) {
        mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

int main(void)
{
    int n;
    int i;

    scanf("%d %d", &n, &k);

    for (i = 0; i < n; ++i) {
        scanf("%d", &a[i]);
    }

    merge_sort(a, 0, n - 1);
    printf("%d\n", result);

    return 0;
}
