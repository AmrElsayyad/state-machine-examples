#include "Light.h"
#include <iostream>
using namespace std;

int main() {
  // Create a light object
  Light light;

  // Toggle the light four times
  for (int i = 0; i < 4; i++) {
    cout << "Current state: " << light.getCurrentState()->toString() << endl;
    cout << "Toggling light..." << endl;
    light.toggle();
    cout << "New state: " << light.getCurrentState()->toString() << endl;
    cout << "-----------------" << endl;
  }

  return 0;
}