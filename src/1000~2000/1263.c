#include <stdio.h>

typedef struct {
    int deadline;
    int duration;
} Task;

int numTasks;
Task tasks[100000];
int answer = -1;

int canCompleteAll(int startTime) 
{
    int currentTime = startTime;
    int i;

    for (i = 0; i < numTasks; i++) {
        if (currentTime + tasks[i].duration > tasks[i].deadline) {
            return 0;
        }
        currentTime += tasks[i].duration;
    }

    return 1;
}

void swapTasks(int i, int j) 
{
    int temp;

    temp = tasks[i].deadline;
    tasks[i].deadline = tasks[j].deadline;
    tasks[j].deadline = temp;

    temp = tasks[i].duration;
    tasks[i].duration = tasks[j].duration;
    tasks[j].duration = temp;
}

void sortTasks(voic) 
{
    int i;
    int j;

    for (i = 0; i < numTasks - 1; i++) {
        for (j = i + 1; j < numTasks; j++) {
            if (tasks[i].deadline > tasks[j].deadline) {
                swapTasks(i, j);
            }
        }
    }
}

int main(void) 
{
    int i;

    scanf("%d", &numTasks);
    for (i = 0; i < numTasks; i++) {
        scanf("%d", &tasks[i].duration);
        scanf("%d", &tasks[i].deadline);
    }

    sortTasks();

    int left = 0;
    int right = 1000000000;

    while (left <= right) {
        int startTime = (left + right) / 2;

        if (canCompleteAll(startTime)) {
            answer = startTime;
            left = startTime + 1;
        } else {
            right = startTime - 1;
        }
    }

    printf("%d\n", answer);
    return 0;
}
