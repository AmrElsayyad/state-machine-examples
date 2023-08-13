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
} eSystemState;

//Different type events
typedef enum
{
    Card_Insert_Event,
    Pin_Enter_Event,
    Option_Selection_Event,
    Amount_Enter_Event,
    Amount_Dispatch_Event
} eSystemEvent;

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
    while(1)
    {
        eNewEvent = ReadEvent();
        switch(eNextState)
        {
        case Idle_State:
        {
            if(Card_Insert_Event == eNewEvent)
            {
                eNextState = InsertCardHandler();
            }
        }
        break;
        case Card_Inserted_State:
        {
            if(Pin_Enter_Event == eNewEvent)
            {
                eNextState = EnterPinHandler();
            }
        }
        break;
        case Pin_Eentered_State:
        {
            if(Option_Selection_Event == eNewEvent)
            {
                eNextState = OptionSelectionHandler();
            }
        }
        break;
        case Option_Selected_State:
        {
            if(Amount_Enter_Event == eNewEvent)
            {
                eNextState = EnterAmountHandler();
            }
        }
        break;
        case Amount_Entered_State:
        {
            if(Amount_Dispatch_Event == eNewEvent)
            {
                eNextState = AmountDispatchHandler();
            }
        }
        break;
        default:
            break;
        }
    }
    return 0;
}
