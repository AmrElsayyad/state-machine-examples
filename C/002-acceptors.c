#include <stdio.h>


// Define the state type as a function pointer
typedef void *(*state_t)(const char);


/***********************
 * Function Prototypes *
 ***********************/
void *state_n(const char input);
void *state_i(const char input);
void *state_c(const char input);
void *state_e(const char input);
void *state_success(const char input);
int test_fsm(const char *str);


/*****************
 * Main Function *
 *****************/
int main() {

    // Run the FSM with some inputs
    test_fsm("nice");
    test_fsm("nick");
}


/************************
 * Function Definitions *
 ************************/

int test_fsm(const char *str) {
    state_t state = state_n;
    
    for (int i = 0; str[i]; i++) {
        state = state(str[i]); // Update the state with the input
        if (state == NULL) {
            printf("Error\n");
            return 1;
        }
    }

    printf("Success\n");
    return 0;
}

void *state_n(const char input) {
    if (input == 'n') return state_i;
    return NULL;
}


void *state_i(const char input) {
    if (input == 'i') return state_c;
    return NULL;
}

;
void *state_c(const char input) {
    if (input == 'c') return state_e;
    return NULL;
}

void *state_e(const char input) {
    if (input == 'e') return state_success;
    return NULL;
}

void *state_success(const char input) {
    printf("%c\n", input);
    return NULL;
}
