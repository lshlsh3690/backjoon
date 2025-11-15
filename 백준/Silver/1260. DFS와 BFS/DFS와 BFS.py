from copy import deepcopy
from collections import deque
n, m, v = map(int, input().split())

arr = [[0 for _ in range(n+1)] for _ in range(n+1)]
arr2 = deepcopy(arr)

for _ in range(m):
    i,j = map(int,input().split())
    arr[i][j] = 1
    arr[j][i] = 1
    arr2[i][j] = 1
    arr2[j][i] = 1

def dfs(start):
    print(start, end=' ')
    for i in range(1,n+1):
        if arr[start][i] == 1:
            for k in range(1,n+1):
                arr[k][i] = 0
            dfs(i)

queue = deque()
def bfs():
    global queue
    while queue:
        start = queue.popleft()
        print(start, end=' ')
        for i in range(1, n + 1):
            if arr2[start][i] == 1:
                for k in range(1, n + 1):
                    arr2[k][i] = 0
                queue.append(i)



for i in range(1,n+1):
    arr[i][v] = 0
dfs(v)
print()
queue.append(v)
for i in range(1,n+1):
    arr2[i][v] = 0
bfs()