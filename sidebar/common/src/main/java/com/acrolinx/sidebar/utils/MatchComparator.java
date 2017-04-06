/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.utils;

import java.io.Serializable;
import java.util.Comparator;

import com.acrolinx.sidebar.pojo.document.AbstractMatch;

public class MatchComparator implements Comparator<AbstractMatch>, Serializable
{
    @Override
    public int compare(AbstractMatch o1, AbstractMatch o2)
    {
        return Integer.compare(o1.getRange().getMinimumInteger(), o2.getRange().getMinimumInteger());
    }
}
