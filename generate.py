import random
def f(a,b,c,d,e):
	return (31*a+17*b+29*c-26*d-3*e)*random.randint(90,110)/100.0
fl = open('regression.txt','w')
for i in range(1,100):
	a = random.randint(-100,100)
	b = random.randint(-100,100)
	c = random.randint(-100,100)
	d = random.randint(-100,100)		
	e = random.randint(-100,100)	
	fl.write(str(a)+","+str(b)+","+str(c)+","+str(d)+","+str(e)+","+str(f(a,b,c,d,e))+"\n")
fl.close()
