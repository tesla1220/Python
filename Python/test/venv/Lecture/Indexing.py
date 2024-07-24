# index operator [] = gives access to sequence's elements (str, list, tuples)

name = "jina Lee"

# if(name[0].islower()):
#     name = name.capitalize()

first_name = name[:4].upper()
last_name = name[5:].lower()
last_charactor = name[-2]

print(last_charactor)