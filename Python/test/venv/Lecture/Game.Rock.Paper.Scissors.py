import random

# ▶️ 'while loops' are typically used with a condition that is evaluated before each iteration of the loop.
#     The loop continues as long as the condition is True.

# ▶️ 'while True' is a loop that will run indefinitely because True is always true.
#     while True creates an infinite loop that relies on an internal condition or break statement to exit.


while True:
    choose = ["rock", "paper", "scissors"]

    computer = random.choice(choose)
    player = None

    while player not in choose:
            player = input("rock, paper or scissors?: ").lower()

    if player == computer:
            print("computer:", computer)
            print("player : ", player)
            print("Tie!")

    elif player == "rock":
            if computer == "paper":
                    print("computer:", computer)
                    print("player : ", player)
                    print("You win!")
            if computer == "scissors":
                    print("computer:", computer)
                    print("player : ", player)
                    print("Yoy lose!")

    elif player == "scissors":
            if computer == "paper":
                     print("computer:", computer)
                     print("player : ", player)
                     print("You win!")
            if computer == "rock":
                     print("computer:", computer)
                     print("player : ", player)
                     print("Yoy lose!")

    elif player == "paper":
            if computer == "rock":
                     print("computer:", computer)
                     print("player : ", player)
                     print("You win!")
            if computer == "scissors":
                     print("computer:", computer)
                     print("player : ", player)
                     print("Yoy lose!")


    play_again = input("Play again? (yes/no) : ").lower()

    if play_again != "yes":
        break

print("Bye!")