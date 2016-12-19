package com.acrolinx.sidebar;

import com.acrolinx.sidebar.pojo.document.AcrolinxMatch;
import com.acrolinx.sidebar.pojo.document.AcrolinxMatchWithReplacement;
import com.acrolinx.sidebar.pojo.settings.InputFormat;

import java.util.List;

public interface InputAdapterInterface
{

    InputFormat getInputFormat();

    String getContent();

    void selectRanges(String checkId, List<AcrolinxMatch> matches);

    void replaceRanges(String checkId, List<AcrolinxMatchWithReplacement> matchesWithReplacement);

    //registerCheckCall(checkInfo: Check): void;
    //registerCheckResult(checkResult: CheckResult): void;
}
