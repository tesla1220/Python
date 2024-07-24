import os

# path = "empty_folder"
#
# try:
#     # os.remove(path)
#     os.rmdir(path)
# except FileNotFoundError:
#     print("THat file was not found")
# except PermissionError:
#     print("You don't have permission to delete that")
# else:
#     print(path + " was deleted")

import shutil

path = "folder"

try:
    # os.remove(path)           # delete a file
    # os.rmdir(path)              # delete an empty directory
    shutil.rmtree(path)         # delete a directory containing files
except FileNotFoundError:
    print("THat file was not found")
except PermissionError:
    print("You don't have permission to delete that")
except OSError:
    print("You cannot delete that using that function ")
else:
    print(path + " was deleted")