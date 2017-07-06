package io.confluent.connect.hdfs.file;


import io.confluent.connect.hdfs.FileUtils;
import io.confluent.connect.hdfs.HdfsSinkConnectorConstants;
import io.confluent.connect.hdfs.storage.Storage;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;

import java.io.IOException;
import java.util.regex.Matcher;

public class DefaultFileService implements FileService<SinkRecord> {

    @Override
    public void process(SinkRecord record, String encodedPartition) {
        // do nothing
    }

    @Override
    public void reset(String encodedPartition) {
        // do nothing
    }

    @Override
    public String directoryName(String url, String topicsDir, String directory) {
        return FileUtils.directoryName(url, topicsDir, directory);
    }

    @Override
    public String tempFileName(String url, String topicsDir, String directory, String extension) {
        return FileUtils.tempFileName(url, topicsDir, directory, extension);
    }

    @Override
    public String committedFileName(String url, String topicsDir, String directory, TopicPartition topicPart, long startOffset, long endOffset, String encodedPartition, String extension, String zeroPadFormat) {
        return FileUtils.committedFileName(url, topicsDir, directory, topicPart, startOffset, endOffset, extension, zeroPadFormat);
    }

    @Override
    public String topicDirectory(String url, String topicsDir, String topic) {
        return FileUtils.topicDirectory(url, topicsDir, topic);
    }

    @Override
    public FileStatus fileStatusWithMaxOffset(Storage storage, Path path, PathFilter filter) throws IOException {
        return FileUtils.fileStatusWithMaxOffset(storage, path, filter);
    }

    @Override
    public long extractOffset(String filename) {
        return FileUtils.extractOffset(filename);
    }

    @Override
    public FileInfo getFileInfo(String fileName) {
        Matcher m = HdfsSinkConnectorConstants.COMMITTED_FILENAME_PATTERN.matcher(fileName);
        if (!m.matches()) {
            return null;
        } else {
            String topic = m.group(HdfsSinkConnectorConstants.PATTERN_TOPIC_GROUP);
            int partition = Integer.parseInt(m.group(HdfsSinkConnectorConstants.PATTERN_PARTITION_GROUP));
            long startOffset = Long.parseLong(m.group(HdfsSinkConnectorConstants.PATTERN_START_OFFSET_GROUP));
            long endOffset = Long.parseLong(m.group(HdfsSinkConnectorConstants.PATTERN_END_OFFSET_GROUP));

            return new FileInfo(topic, partition, startOffset, endOffset);
        }
    }
}
