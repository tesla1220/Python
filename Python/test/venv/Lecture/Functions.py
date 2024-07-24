# function = a block of code which is executed only when it is called

def hello(first_name, last_name, age):
    print("hello! " + first_name + " " + last_name)
    print("You are " + str(age) + " years old !")
    print("Have a nice day!")


my_name = "Jina"

# hello(my_name)

hello("Jina","Lee", 21)

# hello("Jina")
# hello("Dude")


# We need a matching number of parameters setup to receive arguments

def func_mul(x):
    a = x * 10
    b = x * 20
    c = x * 30
    return a, b, c

a, b, c = func_mul(10)

print(a, b, c)