# 🟠 scope : The region that a variable is recognized.
#         A variable is only available from inside the region it is created.
#         A global and locally scoped versions of a variable can be created.


# name = "Code"           # global scope : available inside & outside functions
#
# def display_name():
#     # name = "Jina"       # local scope : available only inside this function
#     print(name)
#
# display_name()
# print(name)




# 🟠 args = parameter that will pack all arguments into a tuple
#           useful so that a function can accept a varying amount of arguments

# def add(num1, num2) :
#     sum = num1 + num2
#     return sum
#
# print(add(1,2,3))         # error : third args


# * (asterisk) : 가변 인자 (Variadic Arguments) - 함수 정의에서 *는 가변 개수의 인자를 의미합니다.
#  즉, 함수가 호출될 때 몇 개의 인자를 받을지 미리 정하지 않고, 임의의 개수의 인자를 받을 수 있게 해줍니다. args는 튜플로 저장됩니다.

def add(*args):
    sum = 0                     # sum 변수를 0으로 초기화
    for i in args:              # args 튜플에 있는 각 요소를 순서대로 하나씩 꺼내서 i에 대입
        sum += i                # sum = sum + i => sum에 i를 더한 값을 다시 sum에 저장
    return sum

print(add(1,2,3,4,5))


# 전체 과정
#
#     1. add(1, 2, 3, 4, 5)를 호출하면, 인자 1, 2, 3, 4, 5가 args라는 튜플 (1, 2, 3, 4, 5)로 함수 내부에 전달됩니다.
#     2. sum 변수를 0으로 초기화합니다.
#     3. for 루프를 통해 args 튜플의 각 요소를 순회하면서 sum에 더합니다:
#       for i in args:는 args 튜플에 있는 각 요소를 순서대로 하나씩 꺼내서 i에 대입하고,
#       sum += i는 sum = sum + i와 동일한 의미로, sum에 i를 더한 값을 다시 sum에 저장하는 것을 의미합니다.
#       이렇게 해서 모든 인자를 합산합니다.

#         첫 번째 루프: sum은 0 + 1 = 1
#         두 번째 루프: sum은 1 + 2 = 3
#         세 번째 루프: sum은 3 + 3 = 6
#         네 번째 루프: sum은 6 + 4 = 10
#         다섯 번째 루프: sum은 10 + 5 = 15
#     최종적으로 sum은 15가 되고, 이를 반환합니다.
#     print 함수가 반환된 결과인 15를 출력합니다.


# def add(*stuff):
#     sum = 0
#     stuff = list(stuff)         # cast to list for transforming the type (cuz tuple doesn't support assignment)
#     stuff[0] = 0                # assign the object
#     for i in stuff:
#         sum += i
#     return sum
#
# print(add(1,2,3,4,5))
