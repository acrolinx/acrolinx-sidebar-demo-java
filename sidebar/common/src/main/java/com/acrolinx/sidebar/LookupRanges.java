/*
 * Copyright (c) 2017 Acrolinx GmbH
 */

package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.document.AbstractMatch;

import java.util.List;
import java.util.Optional;

public abstract class LookupRanges
{

    public abstract Optional<List<? extends AbstractMatch>> getMatchesWithCorrectedRanges(String checkedText,
            String changedText, List<? extends AbstractMatch> matches);

}
