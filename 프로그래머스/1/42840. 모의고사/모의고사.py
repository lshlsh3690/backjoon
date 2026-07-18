def solution(answers):
    answer = [0,0,0]

    person1 = "12345"
    person2 = "21232425"
    person3 = "3311224455"


    for i in range(len(answers)):
        if int(person1[i % len(person1)]) == answers[i]:
            answer[0] += 1
        if int(person2[i % len(person2)]) == answers[i]:
            answer[1] += 1
        if int(person3[i % len(person3)]) == answers[i]:
            answer[2] += 1
    
    max_number = max(answer)

    if answer.count(max_number) == 1:
        idx = answer.index(max_number)
        answer= [idx+1]
    else:
        answer = [i+1 for i, v in enumerate(answer) if v==max_number]

    return answer
