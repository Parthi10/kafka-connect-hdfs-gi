package io.confluent.connect.hdfs.file;


public class FileInfo {

    private final String topic;
    private final int partition;
    private final long startOffset;
    private final long endOffset;

    public FileInfo(String topic, int partition, long startOffset, long endOffset) {
        this.topic = topic;
        this.partition = partition;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public String getTopic() {
        return topic;
    }

    public int getPartition() {
        return partition;
    }

    public long getStartOffset() {
        return startOffset;
    }

    public long getEndOffset() {
        return endOffset;
    }
}
