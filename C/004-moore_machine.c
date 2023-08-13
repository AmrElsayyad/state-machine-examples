#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define CLOSING_DELAY 5

// Define the state type as a function pointer
typedef void *(*state_t)();


/***********************
 * Function Prototypes *
 ***********************/
void *state_closed();
void *state_opening();
void *state_open();
void *state_closing();


char password[256];

/*****************
 * Main Function *
 *****************/
int main() {
    // Define the initial state
    state_t state = state_closed;

    printf("Enter new password: ");
    scanf("%s", password);
    getchar();

    // Run the FSM
    for (;;) {
        state = state(); // Update the state
        if (state == NULL) break; // End the FSM if the state is NULL
    }
}


/************************
 * Function Definitions *
 ************************/
void *state_closed() {
    char c = '\n';
    printf("State Closed\n");
    printf("Enter 1 to open, or any other character to exit.\n");
    while (c == '\n') {
        scanf("%c", &c);
    }
    switch (c - '0') {
        case 0: return state_closed;
        case 1: {
            char buff[256];
            printf("Enter password: ");
            scanf("%s", buff);
            getchar();
            if (strncmp(buff, password, strlen(password))) {
                printf("Incorrect password!\n");
                return state_closed;
            }
            
            return state_opening;
        }
        default: return NULL;
    }
}

void *state_opening() {
    printf("Opening...\n");
    return state_open;
}

void *state_open() {
    printf("State Open\n");
    return state_closing;
}

void *state_closing() {
    for (int i = 0; i < CLOSING_DELAY; ++i) {
        printf("\rClosing in %d seconds...", CLOSING_DELAY - i);
        fflush(stdout);
        sleep(1);
    }
    printf("\nClosing...\n");
    return state_closed;
}