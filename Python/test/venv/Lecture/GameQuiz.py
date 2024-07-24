
questions = ("How many elements are in the periodic table?: ",
             "Which animal lays the largest eggs?: ",
             "What is the most abundant gas in Earth's atmosphere?: ",
             "How many bones are in the human body?: ",
             "Which planet in the solar system is the hottest?: ")

options = (("A. 116", "B. 117", "C. 118", "D. 119"),
           ("A. Whale", "B. Crocodile", "C. Elephant", "D. Ostrich"),
           ("A. Nitrogen", "B. Oxygen", "C. Carbon-Dioxide", "D. Hydrogen"),
           ("A. 206", "B. 207", "C. 208", "D. 209"),
           ("A. Mercury", "B. Venus", "C. Earth", "D. Mars"))

answers = ("C", "D", "A", "A", "B")

guess_list = []       # 사용자가 입력한 답을 저장할 리스트
score = 0             # 사용자가 맞춘 정답의 수를 저장할 변수
question_num = 0      # 지금 몇 번째 문제를 물어보는 지에 대한 변수


for each_question in questions:
    print("======================================================")
    print(each_question)
    for every_option in options[question_num]:
        print(every_option)

    guess = input("Enter (A, B, C, D) : ").upper()
    guess_list.append(guess)                                        # 사용자가 입력한 답을 guess_list에 추가
    if guess == answers[question_num]:                              # 사용자의 입력이 정답과 같은지 확인합니다.
        score += 1                                                  # 같다면 점수(score)를 1 증가시키고 "CORRECT"를 출력합니다.
        print("CORRECT")
    else:                                                           # 다르다면 "INCORRECT"를 출력하고, 정답을 알려줍니다.
        print("INCORRECT")
        print(f"{answers[question_num]} is the correct answer")
    question_num += 1                                               # 다음 질문으로 넘어가기 위해 question_num을 1 증가



# if guess == answers[question_num]:
# 이 코드는 사용자가 입력한 답 (guess)이 현재 문제의 정답 (answers[question_num])과 같은지 확인해요.
#
# 예를 들어,
#
#     첫 번째 문제라면 question_num은 0이에요.
#     사용자가 "C"라고 입력했다면, guess는 "C"가 돼요.
#     첫 번째 문제의 정답은 answers[0]인데, 이 값은 "C"예요.
#
# 그러면 if guess == answers[question_num]:는 이렇게 되는 거예요:
#
#     if "C" == "C":
#
# 이 조건은 True이므로, 사용자의 답이 정답이다라고 판단하게 돼요.

