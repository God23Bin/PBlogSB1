package com.bin23.blog.service.label;

import com.bin23.blog.entity.Label;

import java.util.List;

public interface LabelService {
    List<Label> getAllLabel();

    Label getLabelById(Integer id);

    Label getLabelByLabelName(String labelName);

    int addLabel(Label label);

    int updateLabel(Label label);

    int deleteLabelById(Integer id);
}
