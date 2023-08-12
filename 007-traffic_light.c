#include <stdio.h>
#include <string.h>
#include <unistd.h>

// Define the states
typedef enum { RED, YELLOW, GREEN, NUM_STATES } state_t;

// Define the events
typedef enum { TICK, NUM_EVENTS } event_t;

// Define the return codes
typedef enum { OK, ERROR } ret_code_t;

// Define the data structure for the state machine
typedef struct {
  state_t previous_state; // The previous state of the system
  state_t current_state;  // The current state of the system
  int tick_count;         // The number of ticks elapsed in the current state
} sm_data_t;

// Declare the state functions
state_t red_state(sm_data_t *data, event_t event);
state_t yellow_state(sm_data_t *data, event_t event);
state_t green_state(sm_data_t *data, event_t event);

// Declare the transition functions
ret_code_t red_to_yellow(sm_data_t *data);
ret_code_t yellow_to_green(sm_data_t *data);
ret_code_t green_to_yellow(sm_data_t *data);
ret_code_t yellow_to_red(sm_data_t *data);

// Define the array of state functions
state_t (*state_table[NUM_STATES])(sm_data_t *, event_t) = {
    red_state, yellow_state, green_state};

// Define the matrix of transition functions
ret_code_t (*transition_table[NUM_STATES][NUM_STATES])(sm_data_t *) = {
    {NULL, red_to_yellow, NULL},
    {yellow_to_red, NULL, yellow_to_green},
    {NULL, green_to_yellow, NULL}};

// Define the number of ticks for each state
#define RED_TICKS 5
#define YELLOW_TICKS 2
#define GREEN_TICKS 4

// Define a function to print the current state and tick count
void print_state(sm_data_t data) {
  char *state;
  switch (data.current_state) {
  case RED:
    state = strdup("\x1b[31mRED\x1b[0m");
    break;
  case YELLOW:
    state = strdup("\x1b[33mYELLOW\x1b[0m");
    break;
  case GREEN:
    state = strdup("\x1b[32mGREEN\x1b[0m");
    break;
  default:
    break;
  }
  printf("State: %s, Tick: %d\n", state, data.tick_count);
}

// The main function
int main(void) {
  // Initialize the state machine data
  sm_data_t data;
  data.previous_state = YELLOW;
  data.current_state = RED;
  data.tick_count = 0;

  // Run the state machine
  for (;;) {
    // Get the next state from the current state and event
    state_t next_state = state_table[data.current_state](&data, TICK);

    // Get the transition function from the current and next state
    ret_code_t (*transition)(sm_data_t *) =
        transition_table[data.current_state][next_state];

    // Execute the transition function if it exists
    if (transition != NULL) {
      ret_code_t rc = transition(&data);
      if (rc == ERROR) {
        printf("Error occurred during transition\n");
        break;
      }
    }

    // Update the current state
    data.current_state = next_state;

    // Print the current state and tick count
    print_state(data);

    // Sleep for one second
    sleep(1);
  }

  return 0;
}

// The state function for the red state
state_t red_state(sm_data_t *data, event_t event) {
  data->tick_count++;

  if (event == TICK && data->tick_count == RED_TICKS) {
    return YELLOW;
  }

  return RED;
}

// The state function for the yellow state
state_t yellow_state(sm_data_t *data, event_t event) {
  data->tick_count++;

  if (event == TICK && data->tick_count == YELLOW_TICKS) {
    if (data->previous_state == GREEN) {
      return RED;
    } else {
      return GREEN;
    }
  }

  return YELLOW;
}

// The state function for the green state
state_t green_state(sm_data_t *data, event_t event) {
  data->tick_count++;

  if (event == TICK && data->tick_count == GREEN_TICKS) {
    return YELLOW;
  }

  return GREEN;
}

// The transition function from red to yellow
ret_code_t red_to_yellow(sm_data_t *data) {
  // Reset the tick count
  data->tick_count = 0;

  // Set previous state to red
  data->previous_state = RED;

  // Return OK
  return OK;
}

// The transition function from yellow to green
ret_code_t yellow_to_green(sm_data_t *data) {
  // Reset the tick count
  data->tick_count = 0;

  // Set previous state to yellow
  data->previous_state = YELLOW;

  // Return OK
  return OK;
}

// The transition function from green to yellow
ret_code_t green_to_yellow(sm_data_t *data) {
  // Reset the tick count
  data->tick_count = 0;

  // Set previous state to green
  data->previous_state = GREEN;

  // Return OK
  return OK;
}

// The transition function from yellow to red
ret_code_t yellow_to_red(sm_data_t *data) {
  // Reset the tick count
  data->tick_count = 0;

  // Set previous state to yellow
  data->previous_state = YELLOW;

  // Return OK
  return OK;
}
