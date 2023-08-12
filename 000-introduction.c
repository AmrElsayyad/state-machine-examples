#include <stdio.h>


// Define the state type as a function pointer
typedef void *(*state_t)(int);


/***********************
 * Function Prototypes *
 ***********************/
void *state_A(int input);
void *state_B(int input);
void *state_C(int input);


/*****************
 * Main Function *
 *****************/
int main() {
    // Define the initial state
    state_t state = state_A;

    // Run the FSM with some inputs
    int inputs[] = {0, 1, 0, 0, 1, 2};
    int n = sizeof(inputs) / sizeof(inputs[0]);
    for (int i = 0; i < n; i++) {
        state = state(inputs[i]); // Update the state with the input
        if (state == NULL) break; // End the FSM if the state is NULL
    }
}


/************************
 * Function Definitions *
 ************************/
void *state_A(int input) {
    printf("State A\n");
    switch (input) {
        case 0: return state_A; // Stay in state A
        case 1: return state_B; // Go to state B
        default: return NULL; // Invalid input, end the FSM
    }
}

void *state_B(int input) {
    printf("State B\n");
    switch (input) {
        case 0: return state_C; // Go to state C
        case 1: return state_A; // Go back to state A
        default: return NULL; // Invalid input, end the FSM
    }
}

void *state_C(int input) {
    printf("State C\n");
    switch (input) {
        case 0: return state_B; // Go back to state B
        case 1: return state_C; // Stay in state C
        default: return NULL; // Invalid input, end the FSM
    }
}
