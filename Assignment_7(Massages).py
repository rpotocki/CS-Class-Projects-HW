# Write a program that does the following:
# 1. Must display an interactive menu
# 2. Users must  be able to add a massage to their schedule, cancel a massage, view their current schedule, and retrieve their current fundraising total
# 3. The program must not crash is users enter invalid inputs
# 4. The program must include atleast 3 functions: 1 main function and 2 helper functions
# 5. You program should automatically being running when the file is running

def display_schedule(schedule):
    """ This function will update the users schedule according to the action taken """
    print "Your day contains:"
    i = 0
    #prints out the schedule in order by it's position in the list with the according massage time (0 : 15 minute)
    while i <= len(schedule)-1:
      print i,":", schedule[i], "minute"
      i += 1

def display_menu():
    """ This function displays the options menu and returns the users selection """
    print
    print "Make a Selection"
    print "1. Add a massage to my schedule"
    print "2. View my current schedule"
    print "3. Cancel a massage from my schedule"
    print "4. Calculate fundraising total"
    print "5. Quit"
    user_selection = int(raw_input("Your Selection: "))
    return user_selection

def run_program():
    """ This program will run the menu and call for the input of the user to do the action they selected """
    print "UW-Madison DPT Massage Fundraiser Scheduler"
    schedule = []
    user_selection = display_menu()
    while user_selection != 5:
        if user_selection not in (1,2,3,4,5):
            print "Menu options are 1-5."
            user_selection = display_menu()
    # X Add a massage to your schedule
        if user_selection == 1:
            massage_length = int(raw_input("Add a (1) 15 minute, (2) 30 minute, or (3) 60 minute massage? "))
            if massage_length not in (1,2,3):
                print "Add options are 1-3."
                user_selection = display_menu()
            if massage_length == 1:
                schedule.append(15)
                user_selection = display_menu()
            if massage_length == 2:
                schedule.append(30)
                user_selection = display_menu()
            if massage_length == 3:
                schedule.append(60)
                user_selection = display_menu()
        # X Displays the current schedule
        if user_selection == 2:
            display_schedule(schedule)
            user_selection = display_menu()
        # X Cancel a massage from my schedule
        if user_selection == 3:
            if len(schedule) == 0:
                print "Unable to cancel a massage, no massages scheduled."
                user_selection = display_menu()
            else:
                x = int(raw_input("Which massage do you want to cancel? "))
                schedule.pop(x)
                user_selection = display_menu()
        # X Calculate fundraising total
        ftotal = (schedule.count(15)*20) + (schedule.count(30)*30) + (schedule.count(60)*60)
        if user_selection == 4:
            print "Your current total is $"+str(ftotal)
            user_selection = display_menu()
    # X Quit the program
    if user_selection == 5:
        print "Goodbye."
        quit()

run_program()
