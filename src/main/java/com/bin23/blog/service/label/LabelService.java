package com.bin23.blog.service.label;

import com.bin23.blog.entity.Label;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LabelService {
    List<Label> getAllLabel();

    Label getLabelById(Integer id);

    Label getLabelByLabelName(String labelName);

    PageInfo<Label> getAllLabelWithPage(Integer pageNum, Integer pageSize);

    int addLabel(Label label);

    int updateLabel(Label label);

    int deleteLabelById(Integer id);

    int getAllLabelCount();
}
