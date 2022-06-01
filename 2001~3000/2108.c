#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void quick_sort(int* arr, int start, int end);

int main(void)
{
    int N;
    int i;
    int* arr;
    int sum = 0;
    int avr;
    int mid;
    int mode;
    int range;
    int cur_count = 1;
    int mode_count = 1;
    int mode_idx;
    int check = 4001;

    scanf("%d", &N);
    arr = malloc(sizeof(int) * N);

    for (i = 0; i < N; ++i) {
        scanf("%d", &arr[i]);
        sum += arr[i];
    }
    quick_sort(arr, 0, N - 1);
    avr = round(sum / (double)N);
    mid = arr[(0 + N - 1) / 2];
    for (i = 1; i < N; ++i) {
        if (arr[i] != arr[i - 1]) {
            if (cur_count > mode_count) {
                mode_count = cur_count;
            }
            cur_count = 1;
        } else {
            cur_count++;
        }
    }

    cur_count = 1;

    if (N == 1) {
        mode = arr[0];
        goto print;
    } else if (mode_count == 1) {
        mode = arr[1];
        goto print;
    }

    for (i = 0; i < N - 1; ++i) {
        if (arr[i] == arr[i + 1]) {
            cur_count++;
            if (cur_count == mode_count && check == 4001) {
                check = arr[i];
            } else if (cur_count == mode_count && check != 4001) {
                mode = arr[i];
                break;
            }
        } else {
            cur_count = 1;
        }
    }

    if (i == N - 1) {
        mode = check;
    }

print:
    range = arr[N - 1] - arr[0];
    printf("%d\n", avr);
    printf("%d\n", mid);
    printf("%d\n", mode);
    printf("%d\n", range);

    return 0;
}

void quick_sort(int* arr, int start, int end)
{
    int left = start;
    int right = end;
    int pivot = arr[(start + end) / 2];
    int tmp;
    do
    {
        while (arr[left] < pivot) {
            left++;
        }
        while (arr[right] > pivot) {
            right--;
        }
        if (left <= right) {
            tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    } while (left <= right);

    if (start < right) {
        quick_sort(arr, start, right);
    }

    if (left < end) {
        quick_sort(arr, left, end);
    }
}
