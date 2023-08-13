#include <stdio.h>
#include <stdlib.h>

//Different state of ATM machine
typedef enum
{
    Idle_State,
    Card_Inserted_State,
    Pin_Eentered_State,
    Option_Selected_State,
    Amount_Entered_State,
    last_State
} eSystemState;

//Different type events
typedef enum
{
    Card_Insert_Event,
    Pin_Enter_Event,
    Option_Selection_Event,
    Amount_Enter_Event,
    Amount_Dispatch_Event,
    last_Event
} eSystemEvent;

//typedef of 2d array
typedef eSystemState (*const afEventHandler[last_State][last_Event])(void);

//typedef of function pointer
typedef eSystemState (*pfEventHandler)(void);

char *Str_Card_Insert_Event = "\x1b[0mCard Insert Event\x1b[0m ";
char *Str_Pin_Enter_Event = "\x1b[0mPin Enter Event\x1b[0m ";
char *Str_Option_Selection_Event = "\x1b[0mOption Selection Event\x1b[0m ";
char *Str_Amount_Enter_Event = "\x1b[0mAmount Enter Event\x1b[0m ";
char *Str_Amount_Dispatch_Event = "\x1b[0mAmount Dispatch Event\x1b[0m ";

//Prototype of eventhandlers
eSystemState AmountDispatchHandler(void)
{
	Str_Amount_Enter_Event = "\x1b[0mAmount Enter Event\x1b[0m";
	Str_Amount_Dispatch_Event = "\x1b[31mAmount Dispatch Event\x1b[0m";
    return Idle_State;
}

eSystemState EnterAmountHandler(void)
{
	Str_Option_Selection_Event = "\x1b[0mOption Selection Event\x1b[0m";
	Str_Amount_Enter_Event = "\x1b[31mAmount Enter Event\x1b[0m";
    return Amount_Entered_State;
}

eSystemState OptionSelectionHandler(void)
{
	Str_Pin_Enter_Event = "\x1b[0mPin Enter Event\x1b[0m";
	Str_Option_Selection_Event = "\x1b[31mOption Selection Event\x1b[0m";
    return Option_Selected_State;
}

eSystemState EnterPinHandler(void)
{
	Str_Card_Insert_Event = "\x1b[0mCard Insert Event\x1b[0m";
	Str_Pin_Enter_Event = "\x1b[31mPin Enter Event\x1b[0m";
    return Pin_Eentered_State;
}

eSystemState InsertCardHandler(void)
{
	Str_Card_Insert_Event = "\x1b[31mCard Insert Event\x1b[0m";
	Str_Amount_Dispatch_Event = "\x1b[0mAmount Dispatch Event\x1b[0m";
    return Card_Inserted_State;
}

eSystemEvent ReadEvent()
{
    int input;
    system("clear");
    printf("Enter event number:\n");
    printf("0 - %s\n", Str_Card_Insert_Event);
    printf("1 - %s\n", Str_Pin_Enter_Event);
    printf("2 - %s\n", Str_Option_Selection_Event);
    printf("3 - %s\n", Str_Amount_Enter_Event);
    printf("4 - %s\n", Str_Amount_Dispatch_Event);
    printf("Event: ");
    scanf("%d", &input);

    switch (input)
    {
        case 0:
            return Card_Insert_Event;
        case 1:
            return Pin_Enter_Event;
        case 2:
            return Option_Selection_Event;
        case 3:
            return Amount_Enter_Event;
        case 4:
            return Amount_Dispatch_Event;
        default:
            return Card_Insert_Event;
    }
}

int main()
{
    eSystemState eNextState = Idle_State;
    eSystemEvent eNewEvent;
    
    // Table to define valid states and event of finite state machine
    static afEventHandler StateMachine =
    {
        [Idle_State] ={[Card_Insert_Event]= InsertCardHandler },
        [Card_Inserted_State] ={[Pin_Enter_Event] = EnterPinHandler },
        [Pin_Eentered_State] ={[Option_Selection_Event] = OptionSelectionHandler},
        [Option_Selected_State] ={[Amount_Enter_Event] = EnterAmountHandler},
        [Amount_Entered_State] ={[Amount_Dispatch_Event] = AmountDispatchHandler},
    };

    while(1)
    {
        eNewEvent = ReadEvent();
        
        //Check NULL pointer and array boundary
        if( ( eNextState < last_State) && (eNewEvent < last_Event) && StateMachine[eNextState][eNewEvent]!= NULL)
        {
            // function call as per the state and event and return the next state of the finite state machine
            eNextState = (*StateMachine[eNextState][eNewEvent])();
        }
        else
        {
            //Invalid
        }
    }

    return 0;
}
