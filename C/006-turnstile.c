#include <stdio.h>
#include <string.h>

// Define ANSI colors
#define RED(str)     "\x1b[31m" str "\x1b[0m"
#define GREEN(str)   "\x1b[32m" str "\x1b[0m"
#define YELLOW(str)  "\x1b[33m" str "\x1b[0m"
#define BLUE(str)    "\x1b[34m" str "\x1b[0m"
#define MAGENTA(str) "\x1b[35m" str "\x1b[0m"
#define CYAN(str)    "\x1b[36m" str "\x1b[0m"

// Define state strings
#define LOCKED       RED("locked")
#define UNLOCKED     GREEN("unlocked")


// Define the state type as a function pointer
typedef void *(*state_t)(char*);


/***********************
 * Function Prototypes *
 ***********************/
void *state_locked(char *input);
void *state_unlocked(char *input);


/*****************
 * Main Function *
 *****************/
int main() {
    // Define the initial state
    state_t state = state_locked;

    // Run the FSM with some inputs
    char inputs[][10] = {
        "push", "coin", "coin", "push", "push", 
        "coin", "push", "invalid", "coin", "push"
    };
    int n = sizeof(inputs) / sizeof(inputs[0]);

    for (int i = 0; i < n; i++) {
        state = state(inputs[i]); // Update the state with the input
        if (state == NULL) break; // End the FSM if the state is NULL
    }

    return 0;
}

/************************
 * Function Definitions *
 ************************/
void *state_locked(char *input) {
    printf("State: " LOCKED "\t\tInput: " BLUE("%s"), input);
    if (strncasecmp(input, "push", 4) == 0) {
        printf("\tTransition: " LOCKED "   -->  " LOCKED "\n");
        return state_locked;
    }
    else if (strncasecmp(input, "coin", 4) == 0) {
        printf("\tTransition: " LOCKED "   -->  " UNLOCKED "\n");
        return state_unlocked;
    }
    else {
      printf(MAGENTA("\nInvalid input! Terminating...\n"));
      return NULL;
    }
}

void *state_unlocked(char *input) {
    printf("State: " UNLOCKED "\t\tInput: " BLUE("%s"), input);
    if (strncasecmp(input, "push", 4) == 0) {
        printf("\tTransition: " UNLOCKED " -->  " LOCKED "\n");
        return state_locked;
    }
    else if (strncasecmp(input, "coin", 4) == 0) {
        printf("\tTransition: " UNLOCKED " -->  " UNLOCKED "\n");
        return state_unlocked;
    }
    else {
      printf(MAGENTA("\nInvalid input! Terminating...\n"));
      return NULL;
    }
}
