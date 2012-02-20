package com.couchbase.sqoop.mapreduce;

import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import com.cloudera.sqoop.mapreduce.AutoProgressMapper;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CouchbaseImportMapper
  extends AutoProgressMapper<Text, SqoopRecord, Text, NullWritable> {

  private Text outkey;
  private LargeObjectLoader lobLoader;

  public CouchbaseImportMapper() {
    outkey = new Text();
  }

  @Override
  protected void setup(Context context)
    throws IOException, InterruptedException {
    this.lobLoader = new LargeObjectLoader(context.getConfiguration(),
        FileOutputFormat.getWorkOutputPath(context));
  }

  @Override
  public void map(Text key, SqoopRecord val, Context context)
    throws IOException, InterruptedException {
    try {
      val.loadLargeObjects(lobLoader);
    } catch (SQLException sqlE) {
      throw new IOException(sqlE);
    }

    outkey.set(val.toString());
    context.write(outkey, NullWritable.get());
  }

  @Override
  protected void cleanup(Context context) throws IOException {
    if (null != lobLoader) {
      lobLoader.close();
    }
  }
}
