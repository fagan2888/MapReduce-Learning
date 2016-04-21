import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LinearRegression {

  public static class RegressionMapper extends Mapper<Object, Text, Text, FloatWritable>
  {
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
	{
		Configuration conf = context.getConfiguration();
		int numFeat = Integer.parseInt(conf.get("features"));
		String[] in = value.toString().split(",");
		if(in.length == numFeat + 1)
		{
			for(int i=0;i<numFeat;i++)
			{
				for(int j=0;j<numFeat;j++)
					context.write(new Text("A"+","+Integer.toString(i)+","+Integer.toString(j)),new FloatWritable(Float.parseFloat(in[i])*Float.parseFloat(in[j])));
				context.write(new Text("B"+","+Integer.toString(i)),new FloatWritable(Float.parseFloat(in[i])*Float.parseFloat(in[numFeat])));
			}
		}
	}
  }

  public static class RegressionReducer extends Reducer<Text,FloatWritable,Text,FloatWritable>
  {
	public void reduce(Text key, Iterable<FloatWritable> values,Context context) throws IOException, InterruptedException 
	{
		float sum = 0;
		for ( FloatWritable val : values) 
		{
			sum = sum + val.get();
		}
		context.write(key, new FloatWritable(sum));
	}
  }

  public static void main(String[] args) throws Exception 
  {
	Configuration conf = new Configuration();
	String numFeat = "4";	
	if(args.length >=3)
	{
		numFeat = args[2];
	}
	conf.set("features",numFeat);
	Job job = Job.getInstance(conf, "regression");
	job.setJarByClass(LinearRegression.class);
	job.setMapperClass(RegressionMapper.class);
	job.setCombinerClass(RegressionReducer.class);
	job.setReducerClass(RegressionReducer.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(FloatWritable.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
