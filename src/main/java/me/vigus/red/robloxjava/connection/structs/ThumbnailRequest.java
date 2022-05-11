package me.vigus.red.robloxjava.connection.structs;

import me.vigus.red.robloxjava.enums.ThumnailBatch;
import me.vigus.red.robloxjava.enums.ThumnailFormat;
import me.vigus.red.robloxjava.enums.ThumnailSize;

public class ThumbnailRequest {
    private long targetId;
    private ThumnailBatch type;
    private ThumnailSize size;
    private ThumnailFormat format;

    public ThumbnailRequest(long targetId, ThumnailBatch type, ThumnailSize size, ThumnailFormat format) {
        this.targetId = targetId;
        this.type = type;
        this.size = size;
        this.format = format;
    }

    public ThumnailFormat getFormat() {
        return this.format;
    }

    public void setFormat(ThumnailFormat format) {
        this.format = format;
    }

    public long getTargetId() {
        return this.targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public ThumnailBatch getType() {
        return this.type;
    }

    public void setType(ThumnailBatch type) {
        this.type = type;
    }

    public ThumnailSize getSize() {
        return this.size;
    }

    public void setSize(ThumnailSize size) {
        this.size = size;
    }
    
}
