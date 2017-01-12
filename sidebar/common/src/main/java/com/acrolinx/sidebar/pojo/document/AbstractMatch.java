/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.pojo.document;

import org.apache.commons.lang.math.IntRange;

public abstract class AbstractMatch
{
    public abstract IntRange getRange();

    public abstract void setRange(IntRange range);
}
