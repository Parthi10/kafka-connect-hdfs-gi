package io.confluent.connect.hdfs.file;


import io.confluent.connect.hdfs.storage.Storage;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;

public interface FileService<T> {

    void process(T record, String encodedPartition);

    void reset(String encodedPartition);

    String directoryName(String url, String topicsDir, String directory);

    String tempFileName(String url, String topicsDir, String directory, String extension);

    String committedFileName(String url, String topicsDir, String directory, TopicPartition topicPart, long startOffset, long endOffset, String encodedPartition, String extension, String zeroPadFormat);

    String topicDirectory(String url, String topicsDir, String topic);

    FileStatus fileStatusWithMaxOffset(Storage storage, Path path, PathFilter filter) throws IOException;

    long extractOffset(String filename);

    FileInfo getFileInfo(String fileName);
}
