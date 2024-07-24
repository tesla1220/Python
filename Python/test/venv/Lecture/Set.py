# set = collection which is unordered, unindexed. No duplicate values

utensils = {"fork", "spoon", "knife"}

dishes = {"bowl", "cup", "plate", "knife"}

# utensils.add("napkin")
# utensils.remove("fork")
# utensils.clear()

# utensils.update(dishes)
# dishes.update(utensils)

# dinner_table = utensils.union(dishes)

# print(utensils.difference(dishes))      # what do utensils have that dishes doesn't
# print(dishes.difference(utensils))

# for x in dinner_table:
#     print(x)                    # Not

print(utensils.intersection(dishes))        # whatever they have in common

