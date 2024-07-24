# dictionary = A changeable, unordered collection of unique 'key:value' pairs
#              Fast Because they use hashing, allow us to access a value quickly

capitals = {'USA':'Washington D.C',
            'India':'New Deli',
            'China':'Beijing',
            'Russia':'Moscow'}

capitals.update({'Germany':'Berlin'})
capitals.update({'USA':'Las Vegas'})
capitals.pop('China')
capitals.clear()


# print(capitals['Russia'])       # Moscow
# print(capitals.get('Korea'))    # None
#
# print(capitals.keys())      # dict_keys(['USA', 'India', 'China', 'Russia'])
# print(capitals.values())    # dict_values(['Washington D.C', 'New Deli', 'Beijing', 'Moscow'])

# print(capitals.items())
            # dict_items([('USA', 'Washington D.C'), ('India', 'New Deli'), ('China', 'Beijing'), ('Russia', 'Moscow')])


for key,value in capitals.items():
    print(key,value)