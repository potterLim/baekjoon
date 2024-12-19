#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_SIZE 600000

typedef struct stack {
    char data[MAX_SIZE];
    int top;
} stack_t;

void stack_init(stack_t *stack) 
{
    stack->top = -1;
}

void stack_push(stack_t *stack, char value) 
{
    if (stack->top < MAX_SIZE - 1) {
        stack->data[++(stack->top)] = value;
    }
}

char stack_pop(stack_t *stack) 
{
    if (stack->top >= 0) {
        return stack->data[(stack->top)--];
    }
    return '\0';
}

int stack_is_empty(stack_t *stack) 
{
    return stack->top == -1;
}

char stack_peek(stack_t *stack) 
{
    if (stack->top >= 0) {
        return stack->data[stack->top];
    }
    return '\0';
}

int main(void) 
{
    char initial_text[MAX_SIZE + 1];
    int num_commands;
    stack_t left_stack;
    stack_t right_stack;

    stack_init(&left_stack);
    stack_init(&right_stack);

    scanf("%s", initial_text);

    for (int i = 0; initial_text[i] != '\0'; i++) {
        stack_push(&left_stack, initial_text[i]);
    }

    scanf("%d", &num_commands);

    while (num_commands != 0) {
        char command;
        scanf(" %c", &command);

        if (command == 'L') {
            if (stack_is_empty(&left_stack) == 0) {
                stack_push(&right_stack, stack_pop(&left_stack));
            }
        } else if (command == 'D') {
            if (stack_is_empty(&right_stack) == 0) {
                stack_push(&left_stack, stack_pop(&right_stack));
            }
        } else if (command == 'B') {
            if (stack_is_empty(&left_stack) == 0) {
                stack_pop(&left_stack);
            }
        } else if (command == 'P') {
            char value;
            scanf(" %c", &value);
            stack_push(&left_stack, value);
        }
        num_commands--;
    }

    while (stack_is_empty(&left_stack) == 0) {
        stack_push(&right_stack, stack_pop(&left_stack));
    }

    while (stack_is_empty(&right_stack) == 0) {
        putchar(stack_pop(&right_stack));
    }

    return 0;
}
