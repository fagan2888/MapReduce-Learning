import numpy as np
import sys
L = int(sys.argv[1])
f = open("out.txt","r")
s = f.read().split("\n")
A = np.zeros((L,L))
B = np.zeros((1,L))
for i in range(L*L):
	t = s[i].split("\t")
	in1 = int(t[0].split(",")[1])
	in2 = int(t[0].split(",")[2]	)
	v = float(t[1])
	A[in1][in2] = v
for i in range(L*L,L*L+L):
	t = s[i].split("\t")
	in1 = int(t[0].split(",")[1])
	v = float(t[1])
	B[0][in1] = v
Ainv = np.linalg.inv(A)
B = np.transpose(B)
print np.dot(Ainv,B)
