# tuple = collection which is ordered and unchangeable
#         used to group together related data

#  튜플의 특징
#
#     1. 변경 불가능함(Immutable): 튜플의 요소는 한 번 설정되면 변경할 수 없습니다.
#     2. 순서가 있음(Ordered): 튜플의 요소는 순서가 있으며, 인덱스를 통해 접근할 수 있습니다.
#     3. 중복을 허용함: 튜플은 동일한 값을 여러 번 포함할 수 있습니다.

student = ("Jina",21,"female")

print(student.count("Jina"))    # 결과 : 1    count : how many times it appears
print(student.index("female"))  # 결과 : 2    index : what index it's placed on

for x in student:
    print(x)

if "Jina" in student:
    print("Jina is here !")