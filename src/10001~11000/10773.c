#include <stdio.h>
#include <stdlib.h>

#define MAX_K 100000

int main(void)
{
    int k;
    int* stack;
    int top;
    int num;
    int sum;
    size_t i;
    
    scanf("%d", &k);
    
    stack = (int*)malloc(k * sizeof(int));
    if (stack == NULL) {
        return 1;
    }
    
    top = 0;
    
    for (i = 0; i < k; ++i) {
        scanf("%d", &num);
        
        if (num == 0) {
            if (top > 0) {
                --top;
            }
        } else {
            stack[top++] = num;
        }
    }
    
    sum = 0;
    for (i = 0; i < top; ++i) {
        sum += stack[i];
    }
    
    printf("%d\n", sum);
    
    free(stack);
    stack = NULL;
    return 0;
}
