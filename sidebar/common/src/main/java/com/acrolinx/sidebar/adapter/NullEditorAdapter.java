/*
 * Copyright (c) 2016-2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar.adapter;

import java.util.List;

import com.acrolinx.sidebar.InputAdapterInterface;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;

@SuppressWarnings("unused") public class NullEditorAdapter implements InputAdapterInterface
{

    @Override
    public InputFormat getInputFormat()
    {
        return null;
    }

    @Override
    public String getContent()
    {
        return null;
    }

    @Override
    public String getDocumentReference()
    {
        return null;
    }

    @Override
    public void selectRanges(String s, List<AcrolinxMatch> list)
    {
        //
    }

    @Override
    public void replaceRanges(String s, List<AcrolinxMatchWithReplacement> list)
    {
        //
    }
}
