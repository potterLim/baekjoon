#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MEMORY_SIZE 32
#define MAX_BIT 8
#define PC_BIT 5

#define STA 0x00 /* 000xxxxx */
#define LDA 0x20 /* 001xxxxx */
#define BEQ 0x40 /* 010xxxxx */
#define NOP 0x60 /* 011----- */
#define DEC 0x80 /* 100----- */
#define INC 0xA0 /* 101----- */
#define JMP 0xC0 /* 110xxxxx */
#define HLT 0xE0 /* 111----- */

void execute_program(unsigned char memory[MEMORY_SIZE], unsigned char* accumulator) 
{
    unsigned char pc = 0;
    unsigned char opcode;
    unsigned char operand;

    while (1) {
        opcode = memory[pc] & 0xE0;
        operand = memory[pc] & 0x1F;
        
        pc = (pc + 1) & ((1 << PC_BIT) - 1);

        switch (opcode) {
            case STA:
                memory[operand] = *accumulator;
                break;
            case LDA:
                *accumulator = memory[operand];
                break;
            case BEQ:
                if (*accumulator == 0) {
                    pc = operand;
                }
                break;
            case NOP:
                break;
            case DEC:
                *accumulator = (*accumulator - 1) & 0xFF;
                break;
            case INC:
                *accumulator = (*accumulator + 1) & 0xFF;
                break;
            case JMP:
                pc = operand;
                break;
            case HLT:
                return;
            default:
                return;
        }
    }
}

int main(void) 
{
    unsigned char memory[MEMORY_SIZE];
    unsigned char accumulator;
    char buffer[MAX_BIT + 1];
    int i;

    while (1) {
        for (i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = 0;
        }
        
        for (i = 0; i < MEMORY_SIZE; i++) {
            if (scanf("%8s", buffer) == EOF) {
                return 0;
            }
            memory[i] = (unsigned char) strtol(buffer, NULL, 2);
        }

        accumulator = 0;
        execute_program(memory, &accumulator);

        for (i = 7; i >= 0; i--) {
            printf("%d", (accumulator >> i) & 1);
        }
        printf("\n");
    }

    return 0;
}
