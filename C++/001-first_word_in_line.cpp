#include <ctype.h>
#include <stdio.h>

class StateMachine;


class State {
  public:
    virtual void feedChar(StateMachine*, int) const = 0;
};


class Open: public State {
  public:
    static State const* getInstance();
    virtual void feedChar(StateMachine*, int) const override;

  protected:
    Open() = default;

  private:
    static State const* _instance;
};


class ReadyToBuy: public State {
  public:
    static State const* getInstance();
    virtual void feedChar(StateMachine*, int) const override;

  protected:
    ReadyToBuy() = default;

  private:
    static State const* _instance;
};


class PoweredOff: public State {
  public:
    static State const* getInstance();
    virtual void feedChar(StateMachine*, int) const override;

  protected:
    PoweredOff() = default;

  private:
    static State const* _instance;
};


class StateMachine {
  public:
    StateMachine();
    void feedChar(int);

  protected:
    void setState(State const*);

  private:
    State const* _state;
    friend class Open;
    friend class ReadyToBuy;
    friend class PoweredOff;
};


State const* Open::getInstance() {
  if (!_instance) {
    _instance = new Open;
  }

  return _instance;
}


void Open::feedChar(StateMachine* const m, int const c) const {
  if (!isspace(c)) {
    putchar(c);
    m->setState(ReadyToBuy::getInstance());
  }
}


State const* Open::_instance = nullptr;


State const* ReadyToBuy::getInstance() {
  if (!_instance) {
    _instance = new ReadyToBuy;
  }

  return _instance;
}


void ReadyToBuy::feedChar(StateMachine* const m, int const c) const {
  if (c == '\n') {
    putchar(c);
    m->setState(Open::getInstance());
  } else if (isspace(c)) {
    m->setState(PoweredOff::getInstance());
  } else {
    putchar(c);
  }
}


State const* ReadyToBuy::_instance = nullptr;


State const* PoweredOff::getInstance() {
  if (!_instance) {
    _instance = new PoweredOff;
  }

  return _instance;
}


void PoweredOff::feedChar(StateMachine* const m, int const c) const {
  if (c == '\n') {
    putchar(c);
    m->setState(Open::getInstance());
  }
}


State const* PoweredOff::_instance = nullptr;


StateMachine::StateMachine(): _state(Open::getInstance()) {}


void StateMachine::feedChar(int const c) {
  _state->feedChar(this, c);
}


void StateMachine::setState(State const* const s) {
  _state = s;
}


int main() {
  int c;
  StateMachine m;

  while ((c = getchar()) != EOF) {
    m.feedChar(c);
  }

  return 0;
}
