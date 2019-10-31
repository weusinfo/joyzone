package com.joyzone.platform.core.service;


import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopTypeMapper;
import com.joyzone.platform.core.model.ShopTypeModel;

import tk.mybatis.mapper.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopTypeService extends BaseService<ShopTypeModel> {

    @Autowired
    private ShopTypeMapper shopTypeMapper;
    
    @Autowired
    private ChatService chatService;
    
    @Autowired
    private SysParamsService paramsService;

    /**
     * 获取店家类型ID与名称
     * Mr.Gx
     */
    public List<Map<String,Object>> findByShopType(Integer type){
        return shopTypeMapper.findByShopType(type);
    }

    /**
     * 获取店家类型ID与名称
     * Mr.Gx
     */
    public List<Map<String,Object>> findByPid(Long pid){
        return shopTypeMapper.findByPid(pid);
    }

    /**
     * 获取店家类型信息
     * Mr.Gx
     */
    public List<ShopTypeModel> selectByPageSize(Integer pageNum,Integer pageSize,Integer type){
        return shopTypeMapper.selectByPageSize(pageNum,pageSize,type);
    }
    
    public String getGroupIdByTypeId(Long typeId) {
    	return shopTypeMapper.getGroupIdByTypeId(typeId);
    }
    
    /**
     * 添加商户类型及建立部落
     * @param typeModel
     * @return
     */
    public String addShopType(ShopTypeModel typeModel) {
    	if(typeModel.getType() == null) {
    		return "店家类型不能为空";
    	}
    	if(typeModel.getType().equals(1) || typeModel.getType().equals(2)) {
    		return "店家类型不对";
    	}
    	if(StringUtils.isEmpty(typeModel.getName())) {
    		return "店家名称不能为空";
    	}
    	ShopTypeModel model = new ShopTypeModel();
    	model.setName(typeModel.getName());
    	int count = shopTypeMapper.selectCount(model);
    	if(count > 0) {
    		return "已有同类型的店家类型";
    	}
    	if(StringUtils.isEmpty(typeModel.getTypeImage())) {
    		return "类型图片不能为空";
    	}
    	Long owner = paramsService.getTribeOwner();
    	String chatGroupId = chatService.createTeamGroup(owner, "部落-".concat(typeModel.getName()), "商户类型部落群");
    	if(chatGroupId == null) {
    		return "创建部落失败";
    	}
    	List<String> guests = paramsService.getTribberGuests();
    	if(PublicUtil.isNotEmpty(guests)) {
    		guests.forEach(guest -> {
    			chatService.joinTeamGroup(chatGroupId, Long.parseLong(guest));
    		});
    	}
    	typeModel.setChatGroupId(chatGroupId);
    	try {
    		shopTypeMapper.addShopTye(typeModel);
    	}
    	catch(Exception e) {
    		chatService.deleteTeamGroup(chatGroupId);
    		throw new JZException("添加商户类型失败", e);
    	}
    	return null;
    }
    
    public void initTribles() {
    	ShopTypeModel typeModel = new ShopTypeModel();
    	typeModel.setPageNum(0);
    	typeModel.setPageSize(Integer.MAX_VALUE);
    	List<ShopTypeModel> models = shopTypeMapper.select(typeModel);
    	Long owner = paramsService.getTribeOwner();
    	List<String> guests = paramsService.getTribberGuests();
    	for(ShopTypeModel model : models) {
    		if(StringUtils.isEmpty(model.getChatGroupId())) {
    			String groupId = chatService.createTeamGroup(owner, "部落-".concat(model.getName()), "商户类型部落群");
    			if(StringUtil.isNotEmpty(groupId)) {
    				model.setChatGroupId(groupId);
    				guests.forEach(guest -> {
    					chatService.joinTeamGroup(groupId, Long.parseLong(guest));
    				});
    				int i = update(model);
    				if(i != 1) {
    					chatService.deleteTeamGroup(groupId);
    				}
    			}
    		}
    	}
    }
    
    public List<Map<String,Object>> getTribes(){
    	return shopTypeMapper.getTribes();
    }

}
