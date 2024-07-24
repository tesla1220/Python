#                               🟠 파일 존재 유무 확인

import os

# path = "/Users/jina/Codes/Python/test1.py"
#
# if os.path.exists(path):
#     print("That location exists! ")
#     if os.path.isfile(path):
#         print("That is a file! ")
#     elif os.path.isdir(path):
#         print("That is a directory! ")
# else:
#     print("That location doesn't exist! ")


# path= "/Users/jina/Codes/Python/test"
#
# if os.path.exists(path):
#     print("That location exists! ")
#     if os.path.isfile(path):
#         print("That is a file! ")
#     elif os.path.isdir(path):
#         print("That is a directory! ")
# else:
#     print("That location doesn't exist! ")


python = "/Users/jina/Codes/Python/test/venv/Lecture/inputUser.py"

if os.path.exists(python):
    print("That location exists! ")
    if os.path.isfile(python):
        print("That is a file! ")
    elif os.path.isdir(python):
        print("That is a directory ! ")
else:
    print("That location doesn't exist!")