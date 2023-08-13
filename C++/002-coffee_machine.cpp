#include <cstdlib>
#include <iostream>
#include <string.h>

#define COFFEE_PRICE 10

class StateMachine;

class State {
public:
  virtual void run(StateMachine *, const std::string &) const = 0;
};

class Open : public State {
public:
  static State const *getInstance();
  virtual void run(StateMachine *, const std::string &) const override;

protected:
  Open() = default;

private:
  static State const *_instance;
};

class GetDeposit : public State {
public:
  static State const *getInstance();
  virtual void run(StateMachine *, const std::string &) const override;
  static double getAmount();

protected:
  GetDeposit() = default;
  static void setAmount(double amount);

private:
  static State const *_instance;
  static double _amount;
};

class ReadyToBuy : public State {
public:
  static State const *getInstance();
  virtual void run(StateMachine *, const std::string &) const override;

protected:
  ReadyToBuy() = default;

private:
  static State const *_instance;
};

class PoweredOff : public State {
public:
  static State const *getInstance();
  virtual void run(StateMachine *, const std::string &) const override;

protected:
  PoweredOff() = default;

private:
  static State const *_instance;
};

class StateMachine {
public:
  StateMachine();
  void run(const std::string &);

protected:
  void setState(State const *);

private:
  State const *_state;
  friend class Open;
  friend class GetDeposit;
  friend class ReadyToBuy;
  friend class PoweredOff;
};

State const *Open::getInstance() {
  system("clear");
  std::cout << "Coffee Price: " << COFFEE_PRICE
          << "\nCurrent Amount: " << GetDeposit::getAmount()
          << "\nState: Open\nOptions: deposit, shutdown\nInput: ";
  if (!_instance) {
    _instance = new Open;
  }

  return _instance;
}

void Open::run(StateMachine *const m, const std::string &input) const {
  if (strncasecmp(input.c_str(), "deposit", strlen("deposit")) == 0) {
    m->setState(GetDeposit::getInstance());
  } else if (strncasecmp(input.c_str(), "shutdown", strlen("shutdown")) == 0) {
    m->setState(PoweredOff::getInstance());
  } else {
    m->setState(Open::getInstance());
  }
}

State const *Open::_instance = nullptr;

State const *GetDeposit::getInstance() {
  system("clear");
  std::cout << "Coffee Price: " << COFFEE_PRICE
          << "\nCurrent Amount: " << GetDeposit::getAmount()
          << "\nState: GetDeposit\nOptions: deposit, shutdown\nEnter amount: ";
  if (!_instance) {
    _instance = new GetDeposit;
  }

  return _instance;
}

void GetDeposit::run(StateMachine *const m, const std::string &input) const {
  setAmount(getAmount() + atof(input.c_str()));
  if (getAmount() >= COFFEE_PRICE) {
    setAmount(getAmount() - COFFEE_PRICE);
    m->setState(ReadyToBuy::getInstance());
  } else {
    m->setState(Open::getInstance());
  }
}

void GetDeposit::setAmount(double amount) { _amount = amount; }

double GetDeposit::getAmount() { return _amount; }

State const *GetDeposit::_instance = nullptr;
double GetDeposit::_amount = 0;

State const *ReadyToBuy::getInstance() {
  system("clear");
  std::cout << "Coffee Price: " << COFFEE_PRICE
          << "\nCurrent Amount: " << GetDeposit::getAmount()
          << "\nState: Ready\nOptions: dispense\nInput: ";
  if (!_instance) {
    _instance = new ReadyToBuy;
  }

  return _instance;
}

void ReadyToBuy::run(StateMachine *const m, const std::string &input) const {
  if (strncasecmp(input.c_str(), "dispense", strlen("dispense")) == 0) {
    m->setState(Open::getInstance());
  } else {
    m->setState(ReadyToBuy::getInstance());
  }
}

State const *ReadyToBuy::_instance = nullptr;

State const *PoweredOff::getInstance() {
  system("clear");
  std::cout << "Coffee Price: " << COFFEE_PRICE
          << "\nCurrent Amount: " << GetDeposit::getAmount()
          << "\nState: Off\nOptions: start\nInput: ";
  if (!_instance) {
    _instance = new PoweredOff;
  }

  return _instance;
}

void PoweredOff::run(StateMachine *const m, const std::string &input) const {
  if (strncasecmp(input.c_str(), "start", strlen("start")) == 0) {
    m->setState(Open::getInstance());
  } else {
    m->setState(PoweredOff::getInstance());
  }
}

State const *PoweredOff::_instance = nullptr;

StateMachine::StateMachine() : _state(PoweredOff::getInstance()) {}

void StateMachine::run(const std::string &input) { _state->run(this, input); }

void StateMachine::setState(State const *const s) { _state = s; }

int main() {  
  std::string str;
  StateMachine m;

  while (1) {
    std::cin >> str;
    m.run(str);
  }

  return 0;
}
