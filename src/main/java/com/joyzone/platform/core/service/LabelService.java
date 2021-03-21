package com.joyzone.platform.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.RedisUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.LabelMapper;
import com.joyzone.platform.core.model.LabelModel;

import tk.mybatis.mapper.util.StringUtil;

@Transactional
@Service
public class LabelService extends BaseService<LabelModel>{
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private LabelMapper labelMapper;
	/**
	 * 创建动态标签
	 */
	public void addLabel(LabelModel labelModel) {
		if(StringUtil.isEmpty(labelModel.getType())) {
			labelModel.setType(Constants.STRATEGY_LABEL_DEFAULT);
		}
		int i = labelMapper.addLabel(labelModel);
		if(i > 0) {
			redisUtil.hset(Constants.CACHE_STRATEGY_LABEL, labelModel.getId()+"::"+labelModel.getType(), labelModel);
		}
	}
	
	/**
	 * 删除动态标签
	 */
	public void delLabel(String labelId, String type, Long userId) {
		int i = labelMapper.delLabel(labelId, userId);
		String strType = (type.equals("") || type == null) ? Constants.STRATEGY_LABEL_DEFAULT : type;
		if(i > 0) {
			redisUtil.hdel(Constants.CACHE_STRATEGY_LABEL, labelId + "::" + strType);
		}
	}
	
	/**
	 * 更新攻略标签
	 * @param labelModel
	 */
	public void updateLabel(LabelModel labelModel) {
		int i = labelMapper.updateLabel(labelModel);
		labelModel = selectByKey(labelModel.getId());
		if(i > 0) {
			String type = (labelModel.getType().equals("") || labelModel.getType() == null) ? Constants.STRATEGY_LABEL_DEFAULT : labelModel.getType();
			redisUtil.hdel(Constants.STRATEGY_LABEL_DEFAULT, labelModel.getId() + "::" + type);
			redisUtil.hset(Constants.CACHE_STRATEGY_LABEL, labelModel.getId() + "::" + type, labelModel);
		}
	}
	
	/**
	 * Disable label
	 * @param labelId
	 * @param userId
	 */
	public void disableLabel(Long labelId, Long userId, String type) {
		int i = labelMapper.disableModel(labelId, userId);
		if(i > 0) {
			redisUtil.hdel(Constants.CACHE_STRATEGY_LABEL, labelId + "::" + type);
		}
		
	}
}
