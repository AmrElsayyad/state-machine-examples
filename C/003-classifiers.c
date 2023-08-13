#include <stdio.h>

#define DEBUG 1

// Define the state type as an enum
typedef enum {
    STATE_1,
    STATE_2,
    STATE_3,
    STATE_4
} state_t;

// Define the class type as an enum
typedef enum {
    CLASS_A,
    CLASS_B,
    CLASS_C,
    CLASS_REJECT
} class_t;

// Define a struct to store the state and the class
typedef struct {
    state_t state;
    class_t class;
} state_class_t;

// Define the transition function as an array of arrays
state_class_t transition[4][2] = {
    {{STATE_1, CLASS_A}, {STATE_2, CLASS_B}}, // Transitions from state 1
    {{STATE_2, CLASS_B}, {STATE_3, CLASS_C}}, // Transitions from state 2
    {{STATE_3, CLASS_C}, {STATE_4, CLASS_REJECT}}, // Transitions from state 3
    {{STATE_4, CLASS_REJECT}, {STATE_4, CLASS_REJECT}} // Transitions from state 4
};

/***********************
 * Function Prototypes *
 ***********************/
int test_classifier(const char *str);
char *get_class_label(class_t class);


/*****************
 * Main Function *
 *****************/
int main() {

    // Run the classifier with some inputs
    test_classifier("10101");
    test_classifier("00100");
    test_classifier("01010");
    test_classifier("00000");
}


/************************
 * Function Definitions *
 ************************/

int test_classifier(const char *str) {
    state_class_t current = {STATE_1, CLASS_A}, next; // Initialize the current state and class
    int input;
    
    for (int i = 0; str[i]; i++) {
        input = str[i] - '0'; // Convert the input symbol to 0 or 1
        if (input != 0 && input != 1) {
            printf("Error\n");
            return 1;
        }
        next = transition[current.state][input]; // Update the current state and class with the input using the macros or constants
#if DEBUG
        printf("Current state: %d, Next state: %d\n", current.state, next.state); // Print the current and next state
#endif
	current = next;
    }

    printf("The input %s is classified as %s\n", str, get_class_label(current.class));
    return 0;
}

char *get_class_label(class_t class) {
    // Return the corresponding class label as a string
    switch (class) {
        case CLASS_A:
            return "A";
        case CLASS_B:
            return "B";
        case CLASS_C:
            return "C";
        case CLASS_REJECT:
            return "Reject";
        default:
            return NULL;
    }
}
