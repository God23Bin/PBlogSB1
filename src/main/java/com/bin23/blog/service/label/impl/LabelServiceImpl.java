package com.bin23.blog.service.label.impl;

import com.bin23.blog.dao.LabelMapper;
import com.bin23.blog.entity.Label;
import com.bin23.blog.service.label.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public List<Label> getAllLabel() {
        return labelMapper.selectAllLabel();
    }

    @Override
    public Label getLabelById(Integer id) {
        return labelMapper.selectLabelById(id);
    }

    @Override
    public Label getLabelByLabelName(String labelName) {
        return labelMapper.selectLabelByLabelName(labelName);
    }

    @Override
    public int addLabel(Label label) {
        return labelMapper.insertLabelIntoDB(label);
    }

    @Override
    public int updateLabel(Label label) {
        return labelMapper.updateLabel(label);
    }

    @Override
    public int deleteLabelById(Integer id) {
        return labelMapper.deleteLabelById(id);
    }
}
