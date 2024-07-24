# keyword arguments = arguments preceded by an identifier when we pass them to a function
#                     The order of the arguments doesn't matter, unlike positional arguments
#                     Python know the names of the arguments that our function receives

def hello(first, middle, last):
    print("Hello " + first + " " + middle + " " +last)


hello(last="Lee", middle="Hot", first="Jina")