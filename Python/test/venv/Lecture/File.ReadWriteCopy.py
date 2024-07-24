#                       🟠 How to open and read a file using Python

# with open('/Users/jina/Codes/Python/test1.py') as file:
#     print(file.read())


# try:
#     with open('/Users/jina/Codes/Python/test1.p') as file:
#         print(file.read())
# except FileNotFoundError:
#     print("That file was not found :(  ")

# with open('/Users/jina/Desktop/text.rtf') as file:
#     print(file.read())





# #                                   🟠 Write a file
#
# text = "Yoooo\nThis is come text\nHave a good one!\n"
#
# with open('/Users/jina/Codes/Python/test1.py','a') as file:
#     file.write(text)
#                                     # 'w' : write(override)        'a' : append



#                                       🟠 copyfile
# copyfile() =  copies contents of a file
# copy() =      copyfile() + permission mode + destination can be a directory
# copy2() =     copy() + copies metadata (file's creation and modification times)

# import shutil
#
# shutil.copyfile('test1.py', '/Users/jina/Codes/Python/test1.py')   # src.dst
