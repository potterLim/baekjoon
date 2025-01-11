#include <stdio.h>
#include <stdlib.h>

#define MAX_STACK_SIZE 1000000

typedef struct stack {
    int data[MAX_STACK_SIZE];
    int top_index;
} stack_t;

void init_stack(stack_t* stack) 
{
    stack->top_index = -1;
}

int is_empty(stack_t* stack) 
{
    return stack->top_index == -1;
}

int is_full(stack_t* stack) 
{
    return stack->top_index == MAX_STACK_SIZE - 1;
}

void push(stack_t* stack, int value) 
{
    if (!is_full(stack)) {
        stack->top_index++;
        stack->data[stack->top_index] = value;
    }
}

int pop(stack_t* stack) 
{
    if (!is_empty(stack)) {
        int top_value = stack->data[stack->top_index];
        stack->top_index--;
        return top_value;
    }
    return -1;
}

int size(stack_t* stack) 
{
    return stack->top_index + 1;
}

int top(stack_t* stack) 
{
    if (!is_empty(stack)) {
        return stack->data[stack->top_index];
    }
    return -1;
}

int main(void) 
{
    int count_command;
    int type_command;
    int value;
    size_t i;
    stack_t stack;

    init_stack(&stack);

    scanf("%d", &count_command);

    for (i = 0; i < count_command; ++i) {
        scanf("%d", &type_command);

        if (type_command == 1) {
            scanf("%d", &value);
            push(&stack, value);
        } else if (type_command == 2) {
            printf("%d\n", pop(&stack));
        } else if (type_command == 3) {
            printf("%d\n", size(&stack));
        } else if (type_command == 4) {
            printf("%d\n", is_empty(&stack));
        } else if (type_command == 5) {
            printf("%d\n", top(&stack));
        }
    }

    return 0;
}
