#include <stdio.h>


// Define the state type as a function pointer
typedef void *(*state_t)(int);


/***********************
 * Function Prototypes *
 ***********************/
void *state_even(int input);
void *state_odd(int input);


/*****************
 * Main Function *
 *****************/
int main() {
    // Define the initial state
    state_t state = state_even;

    // Run the FSM with inputs from user
    char input_string[1024];
    scanf("%s", input_string);
    for (int i =0; i < input_string[i]; ++i) {
        state = state(input_string[i]); // Update the state with the input
        if (state == NULL) break; // End the FSM if the state is NULL
    }

    if (state == state_even) {
        printf("Binary string has even number of zeros\n");
    } else if (state == state_odd) {
        printf("Binary string has odd number of zeros\n");
    } else {
        printf("Invalid binary string\n");
    }
}


/************************
 * Function Definitions *
 ************************/
void *state_even(int input) {
    switch (input) {
        case '0': return state_odd; // Go to state B
        case '1': return state_even; // Stay in state A
        default: return NULL; // Invalid input, end the FSM
    }
}

void *state_odd(int input) {
    switch (input) {
        case '0': return state_even; // Go to state A
        case '1': return state_odd; // Stay in state B
        default: return NULL; // Invalid input, end the FSM
    }
}
