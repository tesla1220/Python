
# 🟠 nested loops = The "inner loop" will finish all of its iterations before finishing one iteration of the "outer loop"

# rows = int(input("How many rows? : "))
# columns = int(input("How many columns? : "))
# symbol = input("Enter a symbol to use : ")
#
# for i in range(rows):
#     for j in range(columns):
#         print(symbol, end="")
#
# rows = int(input("how many rows ? : "))
# colums = int(input("how many colums ? : " ))
# symbol = input("which symbol ? : ")
#
# for r in range(rows):
#     for c in range(colums):
#         print(symbol, end="")
#     print()

# # 사용자로부터 행(row) 수를 입력받아 정수로 변환하여 변수 'rows'에 저장합니다.
# rows = int(input("How many rows? : "))
#
# # 사용자로부터 열(column) 수를 입력받아 정수로 변환하여 변수 'columns'에 저장합니다.
# columns = int(input("How many columns? : "))
#
# # 사용자로부터 사용할 기호를 입력받아 변수 'symbol'에 저장합니다.
# symbol = input("Enter a symbol to use : ")
#
# # 'rows' 만큼 반복합니다. for 루프를 사용하여 0부터 rows - 1까지 반복합니다. 이는 전체 행(row)의 수만큼 반복하기 위함입니다.
# for i in range(rows):

#     # 각 행마다 'columns' 만큼 반복합니다.
#     for j in range(columns):
#
# 'symbol'을 출력합니다. end="" 매개변수는 출력 후 기본적으로 포함되는 줄바꿈을 제거하여, 같은 행에 연속적으로 기호를 출력하도록 합니다.
#         print(symbol, end="")

#     # 각 row 가 끝날 때마다 줄바꿈을 합니다.
#     print()


# 🌕 예제 🌕
# for i in range(3):       # 외부 반복문
#     for j in range(2):   # 내부 반복문
#         print(f"i: {i}, j: {j}")

# 🔽🔽 실행 결과
# i: 0, j: 0
# i: 0, j: 1
# i: 1, j: 0
# i: 1, j: 1
# i: 2, j: 0
# i: 2, j: 1


# rows = 3
# colums = 5
# symbol = "#"
#
# for i in range(rows):
#     for j in range(colums):
#         print(symbol, end="")
#     print()


rows = 5
columns = 5
haha = "*"

for i in range(rows):
    for j in range(columns):
        print(haha, end="")             # '*' 한줄씩 출력되지 않고 이어서 출력되게 end 사용
    print()                         # 한 열 출력 후 다음 열로 출력