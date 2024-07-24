
# if statement = a block of code that will execute if it's condition is true

# age = int(input("How old are you ? : "))
#
# if age == 100:
#     print("You are a century old!")
# elif age >= 18:
#     print("You are an adult!")
# elif age < 0:
#     print("You haven't been born yet! ")
# else:
#     print("You are a child!")
#


# logical operators (and, or) = used to check if two or more conditional statements

temp = int(input("What is the temperature outside ? : "))

# if temp >= 0 and temp <= 30:            # and : both conditions must be true
#     print("the temperature is good today ! ")
#     print("Go go outside!")
# elif temp < 0 or temp > 30:             # or : one condition must be true
#     print("the temperature is bad today")
#     print("Stay inside !")


# logical operators ( not ) : flipping the result (true -> false, false -> true)
if not(temp >= 0 and temp <= 30) :
    print("the temperature is bad today")
    print("Stay inside !")
elif not(temp < 0 or temp > 30) :
    print("the temperature is good today ! ")
    print("Go go outside!")
       
