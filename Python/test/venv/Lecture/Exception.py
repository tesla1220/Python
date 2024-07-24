# exception = events detected during execution that interrupt the flow of a program


# numerator = int(input("Enter a number to divide : "))
# denominator = int(input("Enter a number to divide by : "))
# result = numerator / denominator
# print(result)
                    # 0으로 나누었을 때 예외 발생 => ZeroDivisionError: division by zero


# try :
#     numerator = int(input("Enter a number to divide : "))
#     denominator = int(input("Enter a number to divide by : "))
#     result = numerator / denominator
#     print(result)
# except Exception:
#     print("Something went wrong :(")



# try :
#     numerator = int(input("Enter a number to divide : "))
#     denominator = int(input("Enter a number to divide by : "))
#     result = numerator / denominator
#     print(result)
# except ZeroDivisionError:
#     print("You can't divide by zero! ")
# except ValueError:
#     print("Enter only numbers plz")
# except Exception:
#     print("something went wrong :( ")


# try :
#     numerator = int(input("Enter a number to divide : "))
#     denominator = int(input("Enter a number to divide by : "))
#     result = numerator / denominator
#     print(result)
# except ZeroDivisionError as e:
#     print(e)
#     print("You can't divide by zero! ")
# except ValueError as e:
#     print(e)
#     print("Enter only numbers plz")
# except Exception as e:
#     print(e)
#     print("something went wrong :( ")


try:
    numerator = int(input("Enter a number to divide : "))
    denominator = int(input("Enter a number to divide by : "))
    result = numerator / denominator
except ZeroDivisionError as e:
    print(e)
    print("You can't divide by zero! ")
except ValueError as e:
    print(e)
    print("Enter only numbers plz")
except Exception as e:
    print(e)
    print("something went wrong :( ")
else:
    print(result)
finally:
    print("This will always execute")