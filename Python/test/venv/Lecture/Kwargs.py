# 🟠**kwargs = parameter that will pack all arguments into a dictionary
#          useful so that a function can accept a varying amount if keyword argument

# def hello(first, last):
#     print("Hello " + first + " " + last)
#
# hello(first="Jina", middle = "Hot", last="Lee") => error : arguments don't match
#

# def hello(**kwargs):
#     print("Hello " + kwargs['first'] + " " + kwargs['last'] )       # kwargs['key']
#
# hello(first="Jina", middle="Hot", last="Lee")

def hello(**kwargs):
    print("Hello", end=" ")
    for key, value in kwargs.items():
        print(value)


# def hello(**kwargs):
#     print("Hello", end=" ")           #end=" "는 기본 줄바꿈 문자를 공백으로 대체합니다. 이로 인해 다음에 출력되는 내용이 같은 줄에 이어서 출력됩니다.
#     for key,value in kwargs.items():
#         print(value,end=" ")                       # print(value)는 각 value마다 줄바꿈을 수행


hello(title="Ms.", first="Jina", middle="Hot", last="Lee")
         # kwargs 딕셔너리는 {'title': 'Ms.', 'first': 'Jina', 'middle': 'Hot', 'last': 'Lee'}로 초기화됩니다.


# end=" "는 출력이 끝난 후에 줄바꿈을 하지 않고 공백을 추가하도록 합니다. 따라서, 다음에 출력되는 내용은 같은 줄에 이어지게 됩니다.
# for key, value in kwargs.items():
#   kwargs 딕셔너리의 모든 키-값 쌍을 순회합니다. 각 키와 값은 key와 value 변수에 할당됩니다.
#   print(value, end=" ")는 각 값(value)을 출력하며, 출력이 끝난 후에 줄바꿈 대신 공백을 추가합니다.