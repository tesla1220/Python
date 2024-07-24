# String Slicing

# slicing = create a substring by extracting elements form another string
#           indexing[] or slice()
#           [start:stop:step]           start index: inclusive / stop index : exclusive / step : 인덱스 몇 칸씩 이동할 지

# 슬라이싱 구문 s[start:end:step]에서 각 부분은 다음과 같은 역할을 합니다:
#
#     start: 슬라이싱을 시작할 인덱스. 생략 시 기본값은 0입니다.
#     end: 슬라이싱을 끝낼 인덱스. 생략 시 문자열의 끝까지 슬라이싱합니다.
#     step: 슬라이싱할 때의 간격. 생략 시 기본값은 1입니다.


name = "Jina Lee"

first_name = name[:5]
last_name = name[5:]
funky_name = name[::4]
reversed_name = name[::-1]

funky_name1 = name[::5]

# print(first_name)
# print(last_name)

# print(funky_name1)
# print(reversed_name)


# website1 = "http://google.com"
# website2 = "http://wikipedia.com"
#
# slice = slice(7,-4)
#
# # print(website1[slice])
# print(website2[slice])
#

website1 = "123/Jina.com"

proceedSlicing = slice(4,-4)            # index 4 부터 뒤에서 4번째 요소까지 slice
print(website1[proceedSlicing])
