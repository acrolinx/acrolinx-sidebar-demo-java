package com.acrolinx.sidebar.utils;

public class OffsetAlign
{
    private final int oldPosition;
    private final int diffOffset;

    public OffsetAlign(int oldPosition, int diffOffset)
    {
        this.oldPosition = oldPosition;
        this.diffOffset = diffOffset;
    }

    public int getOldPosition()
    {
        return oldPosition;
    }

    public int getDiffOffset()
    {
        return diffOffset;
    }
}
