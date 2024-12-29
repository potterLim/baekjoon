#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STACK_SIZE 10000

typedef struct stack {
    int data[MAX_STACK_SIZE];
    int top;
} stack_t;

void stack_init(stack_t* s) 
{
    s->top = -1;
}

int stack_push(stack_t* s, int value) 
{
    if (s->top >= MAX_STACK_SIZE - 1) {
        return 0;
    }

    s->data[++(s->top)] = value;
    return 1;
}

int stack_pop(stack_t* s, int* out_value) 
{
    if (s->top == -1) {
        return 0;
    }

    *out_value = s->data[(s->top)--];
    return 1;
}

int stack_size(const stack_t* s) 
{
    return s->top + 1;
}

int stack_is_empty(const stack_t* s) 
{
    return s->top == -1;
}

int stack_top(const stack_t* s, int* out_value) 
{
    if (s->top == -1) {
        return 0;
    }
    *out_value = s->data[s->top];
    return 1;
}

int main(void) 
{
    int count;
    int value;
    char command[10];
    stack_t stack;

    stack_init(&stack);

    scanf("%d", &count);

    while (count != 0) {
        scanf("%s", command);

        if (strcmp(command, "push") == 0) {
            scanf("%d", &value);
            stack_push(&stack, value);
        } else if (strcmp(command, "pop") == 0) {
            if (stack_pop(&stack, &value)) {
                printf("%d\n", value);
            } else {
                printf("-1\n");
            }
        } else if (strcmp(command, "size") == 0) {
            printf("%d\n", stack_size(&stack));
        } else if (strcmp(command, "empty") == 0) {
            printf("%d\n", stack_is_empty(&stack));
        } else if (strcmp(command, "top") == 0) {
            if (stack_top(&stack, &value)) {
                printf("%d\n", value);
            } else {
                printf("-1\n");
            }
        }
        count--;
    }

    return 0;
}
