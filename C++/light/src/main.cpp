#include "Light.h"
#include <cstdio>
#include <iostream>
using namespace std;

int main() {
  // Create a light object
  Light light;

  while (1) {
    system("clear");
    cout << "Current state: " << light.getCurrentState()->toString() << endl;
    cout << "Press any key to toggle light.";
    getchar();
    light.toggle();
  }

  return 0;
}