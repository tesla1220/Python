# 🟠 list = used to store multiple items in a single variable

food = ["pizza", "burger", "hotdog", "pasta"]

food[0] = "sushi"

# food.append("ice cream")
# food.remove("sushi")
# food.pop()
# food.insert(0, "cake")
# food.sort()         # sort items alphabetically

food.clear()

for variable in food:
    print(variable)

# 🟠 2D list (multi dimensional list)= a list of lists

drinks = ["coffee", "soda", "tea"]
dinner = ["pizza", "burger", "hotdog"]
dessert = ["cake", "ice cream"]

food = [drinks, dinner, dessert]

print(food)     # 결과 : [['coffee', 'soda', 'tea'], ['pizza', 'burger', 'hotdog'], ['cake', 'ice cream']]
print(food[0])      # 결과 : ['coffee', 'soda', 'tea']
print(food[0][0])   # 결과 : coffee
print(food[0][1])   # 결과 : soda

print(food[1][1])   # 결과 : burger