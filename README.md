# MapReduce-Learning
<h2>Linear regression</h2>
    Here we used the formulas
        Y = THETA.transpose() * X
        where,  THETA = [ ( X.transpose() * X ).inverse() ]* [ X.transpose() * Y ],
        X is the input matrix . Each row of X contains {x1,x2,...,xn},
            xi s are set of parameters,
        Y is the Functional value matrix,


    generate.py is used to create the experimental data
  
    LinearRegression.java calculates A and B matrices using Hadoop
      A = ( X.transpose() * X ).inverse() 
      B = X.transpose() * Y
  
    getAnswer.py Takes the output from Hadoop job and calculates
      THETA = A.inverse() * B
      
  
    regression.sh is a combination of all the commands needed to run the whole job
