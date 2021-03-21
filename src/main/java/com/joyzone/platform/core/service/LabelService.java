package com.joyzone.platform.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.LabelMapper;
import com.joyzone.platform.core.model.LabelModel;

@Transactional
@Service
public class LabelService extends BaseService<LabelModel>{
	
	@Autowired
	private LabelMapper labelMaper;
	/**
	 * 创建动态标签
	 */
	public void addLabel(LabelModel labelModel) {
		
	}
	
	/**
	 * 删除动态标签
	 */
	public int delLabel(String labelId, Long userId) {
		
	}
	
	/**
	 * 更新攻略标签
	 * @param labelModel
	 */
	public void updateLabel(LabelModel labelModel) {
		
	}
	
	public void disableLabel(Long labelId, Long userId) {
		
	}
}
