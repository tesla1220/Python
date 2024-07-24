# 🟠 str.format() = optional method that gives users more control when displaying output

# animal = "cow"
# item = "moon"

# print("The " + animal + " jumped over the " + item)
# print("The {} jumped over the {}".format("cow","mood"))
# print("The {} jumped over the {}".format(animal,item))
# print("The {0} jumped over the {1}".format(item,animal))  # positional argument
# print("The {1} jumped over the {0}".format(item,animal))
# print("The {animal} jumped over the {item}".format(animal="cow",item="moon"))  # keyword argument
# print("The {item} jumped over the {animal}".format(animal="cow",item="moon"))

# text = "The {} jumped over the {}"
# print(text.format(animal, item))

# name = "Jina"

# print("Hello, my name is {}".format(name))
# print("Hello, my name is {:10}".format(name))           # adding padding
# print("Hello my name is {:10}. Nice to meet you ! ".format(name))
# print("Hello my name is {:<10}. Nice to meet you ! ".format(name))
# print("Hello my name is {:>10}. Nice to meet you ! ".format(name))
# print("Hello my name is {:^10}. Nice to meet you ! ".format(name))


# number = 3.14159
#
# print("The number pi is {}".format(number))
# print("The number pi is {:.2f}".format(number))     # only display 3.14. f= float

number = 10000000
print("The number is {:.3f}".format(number))            # The number is 10000000.000
print("The number is {:,}".format(number))              # The number is 10,000,000
print("The number is {:b}".format(number))              # The number is 100110001001011010000000  b: binary
print("The number is {:o}".format(number))              # The number is 46113200    o: Octal Number
print("The number is {:X}".format(number))              # The number is 989680   X: hexadecimal number
print("The number is {:E}".format(number))              # The number is 1.000000E+07




# person = {'name':'Jenn', 'age':23}

# sentence = 'My name is ' + person['name'] + ' and I am ' + str(person['age']) + ' years old.'
# print(sentence)

# sentence = 'My name is {} and I am {} years old.'.format(person['name'], person['age'])
# print(sentence)

# sentence = 'My name is {0} and I am {1} years old.'.format(person['name'], person['age'])
# print(sentence)

# sentence = 'My name is {0[name]} and I am {1[age]} years old.'.format(person, person)
# print(sentence)

# sentence = 'My name is {0[name]} and I am {0[age]} years old.'.format(person)
# print(sentence)


# tag = 'h1'
# text = 'This is headline'
#
# sentence = '<{0}> {1} </{0}>'.format(tag, text)       # <h1> This is headline </h1>
# print(sentence)
                    # {0} 은 format 에 전달된 첫 번 째 인자, 즉 tag. {1}은 format 에 전달된 인자 text를 가리킴

# list = ['Jenn', 23]
# sentence = 'My name is {0[0]} and I am {0[1]} years old.'.format(list)
# print(sentence)
                    #  {0}는 list를 의미합니다. 이는 format 메서드의 첫 번째 인자입니다.


