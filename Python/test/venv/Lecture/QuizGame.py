
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

question_num = 0      # 지금 몇 번째 문제를 물어보는 지에 대한 변수


for current_question in questions:
    print("======================================================")
    print(current_question)
    for every_option in options[question_num]:
        print(every_option)

    guess = input("Enter (A, B, C, D) : ").upper()

    if guess == answers[question_num]:                              # 사용자의 입력이 정답과 같은지 확인합니다.
        print("CORRECT")
    else:                                                           # 다르다면 "INCORRECT"를 출력하고, 정답을 알려줍니다.
        print("INCORRECT")
        print(f"{answers[question_num]} is the correct answer")

    question_num += 1                                               # 다음 질문으로 넘어가기 위해 question_num을 1 증가
