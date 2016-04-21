javac -classpath ../hadoop-core-1.2.1.jar LinearRegression.java 
jar cf linReg.jar LinearRegression*.class
python generate.py
hdfs dfs -rm  /user/srijan/input/regression.txt
hdfs dfs -put regression.txt /user/srijan/input
hdfs dfs -rm -r /user/srijan/output*
hadoop jar linReg.jar LinearRegression /user/srijan/input/regression.txt /user/srijan/output 5
rm out.txt
hadoop fs -get /user/srijan/output/p* out.txt
python getAnswer.py 5
