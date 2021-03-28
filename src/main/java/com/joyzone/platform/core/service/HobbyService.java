package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.SystemHobbyDTO;
import com.joyzone.platform.core.mapper.HobbyMapper;
import com.joyzone.platform.core.model.HobbyModel;
import com.joyzone.platform.core.model.ShopModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HobbyService extends BaseService<HobbyModel> {
	
	private Logger logger = LoggerFactory.getLogger(HobbyService.class);
	@Autowired
	private HobbyMapper hobbyMapper;

	public List<SystemHobbyDTO> getSystemHobbyList(Long userId){
		return hobbyMapper.getSystemHobbyList(userId);
	}

	public List<SystemHobbyDTO> getHobbyNamesByType(String hobbyType,Long userId){
		return hobbyMapper.getHobbyNamesByType(hobbyType,userId);
	}

	public int saveUserHobbys(Long userId,Long hobbyId,Integer type){
		if(type == 0){
			//hobbyMapper.saveUserHobbys(userId,hobbyId);
		}
		if(type == 1){
			//hobbyMapper.saveUserHobbys(userId,hobbyId);
		}
		return 0;
	}
}
