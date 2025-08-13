#include <stdio.h>
#include <stdlib.h>

static void write_int_to_buf(char* buf, int* ppos, int v)
{
    char tmp[16];
    int len;
    int i;

    if (v == 0) {
        buf[(*ppos)++] = '0';
        buf[(*ppos)++] = '\n';
        return;
    }
    if (v < 0) {
        buf[(*ppos)++] = '-';
        v = -v;
    }

    len = 0;
    while (v > 0) {
        tmp[len++] = (char)('0' + (v % 10));
        v /= 10;
    }
    for (i = len - 1; i >= 0; --i) {
        buf[(*ppos)++] = tmp[i];
    }
    buf[(*ppos)++] = '\n';
}

int main(void)
{
    int n;
    int cap;
    int* dq;
    int head;
    int tail;
    int size;
    char* out;
    int out_pos;
    int i;

    scanf("%d", &n);

    cap = n > 0 ? n : 1;
    dq = (int*)malloc(sizeof(int) * cap);
    out = (char*)malloc((size_t)(12 * (long)n));
    if (dq == NULL || out == NULL) {
        return 0;
    }

    head = 0;
    tail = 0;
    size = 0;
    out_pos = 0;

    for (i = 0; i < n; ++i) {
        int cmd;
        scanf("%d", &cmd);

        if (cmd == 1) {
            int x;
            scanf("%d", &x);
            head = (head - 1 + cap) % cap;
            dq[head] = x;
            ++size;
        } else if (cmd == 2) {
            int x;
            scanf("%d", &x);
            dq[tail] = x;
            tail = (tail + 1) % cap;
            ++size;
        } else if (cmd == 3) {
            if (size == 0) {
                write_int_to_buf(out, &out_pos, -1);
            } else {
                int v;
                v = dq[head];
                head = (head + 1) % cap;
                --size;
                write_int_to_buf(out, &out_pos, v);
            }
        } else if (cmd == 4) {
            if (size == 0) {
                write_int_to_buf(out, &out_pos, -1);
            } else {
                tail = (tail - 1 + cap) % cap;
                --size;
                write_int_to_buf(out, &out_pos, dq[tail]);
            }
        } else if (cmd == 5) {
            write_int_to_buf(out, &out_pos, size);
        } else if (cmd == 6) {
            write_int_to_buf(out, &out_pos, size == 0 ? 1 : 0);
        } else if (cmd == 7) {
            if (size == 0) {
                write_int_to_buf(out, &out_pos, -1);
            } else {
                write_int_to_buf(out, &out_pos, dq[head]);
            }
        } else if (cmd == 8) {
            if (size == 0) {
                write_int_to_buf(out, &out_pos, -1);
            } else {
                int idx;
                idx = (tail - 1 + cap) % cap;
                write_int_to_buf(out, &out_pos, dq[idx]);
            }
        }
    }

    fwrite(out, 1, (size_t)out_pos, stdout);

    free(dq);
    free(out);
    return 0;
}
